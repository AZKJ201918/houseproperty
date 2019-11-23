package com.azkj.houseproperty.service;

import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.entity.Journalism;
import com.azkj.houseproperty.entity.Particulars;
import com.azkj.houseproperty.entity.Slideshow;

import java.util.List;

public interface IndexService {
    List<Slideshow> SelectCarousel()throws PropertyExcption;

    Particulars SelectParticulars()throws PropertyExcption;

    List<Journalism> SelectJournalism()throws PropertyExcption;
}
