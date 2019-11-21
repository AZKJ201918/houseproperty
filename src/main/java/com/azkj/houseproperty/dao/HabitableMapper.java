package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Habitable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HabitableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Habitable record);

    int insertSelective(Habitable record);

    Habitable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Habitable record);

    int updateByPrimaryKey(Habitable record);

    @Select(" select  id, genre, url, hid, status from habitable where uid =#{id} ")
    List<Habitable> SelectHabitable(Integer id);
}