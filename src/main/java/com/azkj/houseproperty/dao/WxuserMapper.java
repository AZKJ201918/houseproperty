package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Wxuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface WxuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wxuser record);

    int insertSelective(Wxuser record);

    Wxuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wxuser record);

    int updateByPrimaryKey(Wxuser record);

    @Select("SELECT id,uuid ,superiorid FROM wxuser WHERE openid=#{id}")
    Wxuser SelectWxuser(String openid);
}