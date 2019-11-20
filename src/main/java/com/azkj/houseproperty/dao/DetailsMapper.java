package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Details;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Details record);

    int insertSelective(Details record);

    Details selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Details record);

    int updateByPrimaryKey(Details record);

    List<Details> selectParticulars(Integer id);
}