package com.azkj.houseproperty.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.azkj.houseproperty.common.cache.CommonCacheUtil;
import com.azkj.houseproperty.common.constants.Constants;
import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.utils.*;
import com.azkj.houseproperty.dao.DistributionMapper;
import com.azkj.houseproperty.dao.WxuserMapper;
import com.azkj.houseproperty.entity.Distribution;
import com.azkj.houseproperty.entity.UserElement;
import com.azkj.houseproperty.entity.Wxuser;
import com.azkj.houseproperty.service.UserService;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private WxuserMapper wxuserMapper;

    @Autowired
    private CommonCacheUtil commonCacheUtil;


    @Autowired
    private DistributionMapper distributionMapper;
    @Override
    public String wxlogin(String code,String uuid,String encryptedData,String iv) throws PropertyExcption, Base64DecodingException {
        if(code!=null){
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="
                    + Constants.WEXING_STATUS_APPID+"&secret="+ Constants.WEIXING_STATUS_APPSECRET+"&js_code="+code+"&grant_type=authorization_code";
           String jsonId= HttpUtil.post(url);

            JSONObject jsonObject= JSON.parseObject(jsonId);
            String openid= jsonObject.getString("openid");
            String sessionKey=jsonObject.getString("session_key");
            JSONObject detailed= WxUtil.getUserInfo(encryptedData,sessionKey,iv);
            String nickname = detailed.getString("nickname");
            String headimgurl = detailed.getString("headimgurl");
            Wxuser wxuser=wxuserMapper.SelectWxuser(openid);
            wxuser.setWximg(headimgurl);
            wxuser.setWxname(nickname);
            wxuser.setOpenid(openid);

            //判断数据库有没有用户信息
            if(wxuser!=null){
                //判断老用户有没有上级
                if(wxuser.getSuperiorid()==null){
                    wxuser.setSuperiorid(uuid);
                }
                wxuserMapper.updateByPrimaryKeySelective(wxuser);
            }else{
                String uid= UuidUtil.generate();
                wxuser.setUuid(uid);
                //判断是否带了分享人的uuid
                if(uuid!=null){
                    wxuser.setSuperiorid(uuid);
                }
                wxuserMapper.insertSelective(wxuser);
            }
            //缓存数据
            UserElement ue=new UserElement();
            ue.setUuid(MD5Util.getMD5(wxuser.getUuid()));
            ue.setOpenid(wxuser.getOpenid());
            commonCacheUtil.putTokenWhenLogin(ue);
            return wxuser.getUuid();
        }else{
            throw new PropertyExcption("没有code请从新登陆");
        }

    }

    @Override
    public void Note(String phone) throws PropertyExcption {
        String number= RandomNumberCode.verCode();
        commonCacheUtil.cache(phone,number);
        System.out.println(number);
        try {
            MiaodiUtil.execute(phone,number);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InstrDistribution(Distribution distribution) throws PropertyExcption {
        UserElement userElement=commonCacheUtil.getUserByToken(distribution.getUuid());
        //判断用户是否登录了
        if(userElement==null){
            throw new PropertyExcption("请登录");
        }
        //判断验证码
        String  vrCoder= commonCacheUtil.getCacheValue(distribution.getPhone());
        if(distribution.getVerCoder().equals(vrCoder)){
            throw new PropertyExcption("验证码错误");
        }
        distributionMapper.insertSelective(distribution);
    }


}
