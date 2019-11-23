package com.azkj.houseproperty.service.Impl;

import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.dao.JournalismMapper;
import com.azkj.houseproperty.dao.ParticularsMapper;
import com.azkj.houseproperty.dao.SlideshowMapper;
import com.azkj.houseproperty.entity.Journalism;
import com.azkj.houseproperty.entity.Particulars;
import com.azkj.houseproperty.entity.Slideshow;
import com.azkj.houseproperty.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("IndexServiceImpl")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SlideshowMapper slideshowMapper;

    @Autowired
    private ParticularsMapper particularsMapper;

    @Autowired
    private JournalismMapper journalismMapper;
    @Override
    public List<Slideshow> SelectCarousel() throws PropertyExcption {
        return slideshowMapper.SelectCarousel();
    }

    @Override
    public Particulars SelectParticulars() throws PropertyExcption {
        return particularsMapper.SelectParticulars();
    }

    @Override
    public List<Journalism> SelectJournalism() throws PropertyExcption {
        return journalismMapper.SelectJournalism();
    }
}
