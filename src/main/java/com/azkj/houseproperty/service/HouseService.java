package com.azkj.houseproperty.service;

import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.entity.Details;
import com.azkj.houseproperty.entity.House;
import com.azkj.houseproperty.entity.Piont;

import java.util.List;

public interface HouseService {
    List<House> SelectHoust()throws PropertyExcption;

    List<Details> SelectParticulars(Integer id)throws  PropertyExcption;

    List<House> CheckAttachment(Piont piont) throws PropertyExcption;
}
    