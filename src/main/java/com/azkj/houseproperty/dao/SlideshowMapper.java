package com.azkj.houseproperty.dao;

import com.azkj.houseproperty.entity.Slideshow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);
    @Select("SELECT imgurl,type,linkurl FROM slideshow WHERE status=1 ORDER BY sort DESC")
    List<Slideshow> SelectCarousel();
}