package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    @Select("select id, name, address, url, price, status, phone from house")
    List<House> SelectHoust();
}