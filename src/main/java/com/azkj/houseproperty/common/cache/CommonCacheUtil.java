package com.azkj.houseproperty.common.cache;


import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.utils.MD5Util;
import com.azkj.houseproperty.entity.UserElement;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Map;

@Component
public class CommonCacheUtil {


	private static final String TOKEN_PREFIX = "token.";

	private static final String USER_PREFIX = "user.";

	private Logger logger= LoggerFactory.getLogger(CollectionUtils.class);
	@Autowired
	private JedisPoolWarpper jedisPoolWrapper;


	public void cache(String key, String value) {
		try {
			JedisPool pool = jedisPoolWrapper.getJedisPool();
			if (pool != null) {
				try (Jedis Jedis = pool.getResource()) {
					Jedis.select(0);
					String values=Jedis.get(key);
					if(values==null){
						throw new PropertyExcption(150,"短信验证码未过期");
					}
					Transaction trans = Jedis.multi();
					trans.set(key,value);
					trans.expire(key,2592000);
					trans.exec();
				}
			}
		} catch (Exception e) {
			logger.error("Fail to cache value", e);
		}
	}


	public String getCacheValue(String key) {
		String value = null;
		try {
			JedisPool pool = jedisPoolWrapper.getJedisPool();
			if (pool != null) {
				try (Jedis Jedis = pool.getResource()) {
					Jedis.select(0);
					value = Jedis.get(key);
				}
			}
		} catch (Exception e) {
			logger.error("Fail to get cached value", e);
		}
		return value;
	}


	public long cacheNxExpire(String key, String value, int expiry) {
		long result = 0;
		try {
			JedisPool pool = jedisPoolWrapper.getJedisPool();
			if (pool != null) {
				try (Jedis jedis = pool.getResource()) {
					jedis.select(0);
					result = jedis.setnx(key, value);
					jedis.expire(key, expiry);
				}
			}
		} catch (Exception e) {
			logger.error("Fail to cacheNx value", e);
		}
		
		return result;
	}


	public void delKey(String key) {
		JedisPool pool = jedisPoolWrapper.getJedisPool();
		if (pool != null) {

			try (Jedis jedis = pool.getResource()) {
				jedis.select(0);
				try {
					jedis.del(key);
				} catch (Exception e) {
					logger.error("Fail to remove key from redis", e);
				}
			}
		}
	}



	public void putTokenWhenLogin(UserElement ue) {
		JedisPool pool = jedisPoolWrapper.getJedisPool();
		if (pool != null) {

			try (Jedis jedis = pool.getResource()) {
				jedis.select(0);
				Transaction trans = jedis.multi();
				try {
					trans.del(TOKEN_PREFIX + ue.getUuid());
					trans.hmset(TOKEN_PREFIX + ue.getUuid(), ue.toMap());
					trans.expire(TOKEN_PREFIX + ue.getUuid(), 2592000);
					trans.sadd(USER_PREFIX + ue.getOpenid(), ue.getOpenid());
					trans.exec();
				} catch (Exception e) {
					trans.discard();
					logger.error("Fail to cache token to redis", e);
				}
			}
		}
	}



	/**
	 *  根据uuid取缓存的用户信息
	 * @param uuid
	 * @return
	 * @throws
	 */
	public UserElement getUserByToken(String uuid) throws PropertyExcption {
		UserElement ue = null;
		String token= MD5Util.getMD5(uuid);
		JedisPool pool = jedisPoolWrapper.getJedisPool();
		if (pool != null) {
			try (Jedis jedis = pool.getResource()) {
				jedis.select(0);
				try {
					Map<String, String> map = jedis.hgetAll(TOKEN_PREFIX + token);
					if (map != null && !map.isEmpty()) {
						ue = UserElement.fromMap(map);
					} else {
						logger.warn("Fail to find cached element for token {}", token);
					}
				} catch (Exception e) {
					logger.error("Fail to get token from redis", e);
					throw new PropertyExcption("Fail to get token content");
				}
			}
		}

		return ue;
	}
}
