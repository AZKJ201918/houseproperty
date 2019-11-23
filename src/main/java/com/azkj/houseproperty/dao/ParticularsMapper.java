package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Particulars;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ParticularsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Particulars record);

    int insertSelective(Particulars record);

    Particulars selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Particulars record);

    int updateByPrimaryKey(Particulars record);

    @Select("SELECT details FROM particulars WHERE status=1")
    Particulars SelectParticulars();
}