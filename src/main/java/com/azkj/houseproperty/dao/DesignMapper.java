package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Design;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DesignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Design record);

    int insertSelective(Design record);

    Design selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Design record);

    int updateByPrimaryKey(Design record);

    @Select("select url type from design where status=1 ")
    List<Design> selectDesign(Integer id);
}