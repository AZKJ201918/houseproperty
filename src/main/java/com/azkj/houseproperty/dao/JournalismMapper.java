package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Journalism;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface JournalismMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Journalism record);

    int insertSelective(Journalism record);

    Journalism selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Journalism record);

    int updateByPrimaryKey(Journalism record);
    @Select("select url,caption,linkurl FROM journalism where status=1 ORDER BY sort DESC")
    List<Journalism> SelectJournalism();
}