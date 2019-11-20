package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Distribution;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DistributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Distribution record);

    int insertSelective(Distribution record);

    Distribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Distribution record);

    int updateByPrimaryKey(Distribution record);
}