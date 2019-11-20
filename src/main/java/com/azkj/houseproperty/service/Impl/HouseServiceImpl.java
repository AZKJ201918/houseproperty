package com.azkj.houseproperty.service.Impl;

import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.utils.MongodbUtil;
import com.azkj.houseproperty.dao.DesignMapper;
import com.azkj.houseproperty.dao.DetailsMapper;
import com.azkj.houseproperty.dao.HouseMapper;
import com.azkj.houseproperty.entity.Coordinates;
import com.azkj.houseproperty.entity.Details;
import com.azkj.houseproperty.entity.House;
import com.azkj.houseproperty.entity.Piont;
import com.azkj.houseproperty.service.HouseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("houseServiceImpl")
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private DesignMapper designMapper;

    @Autowired
    private MongodbUtil mongodbUtil;


    @Override
    public List<House> SelectHoust() throws PropertyExcption{
        return houseMapper.SelectHoust();
    }

    @Override
    public List<Details> SelectParticulars(Integer id) throws PropertyExcption {
        List<Details> detailsList=detailsMapper.selectParticulars(id);
        detailsList.stream().forEach(
                parm->{
                    parm.setDesignList(designMapper.selectDesign(id));
                    parm.setPiont(mongodbUtil.SelectPiont(id).Coordinates());
                }
        );

        return detailsList;

    }

    @Override
    public List<House> CheckAttachment(Piont piont) throws PropertyExcption {
        List<Coordinates> coordinates=mongodbUtil.findthenear(piont);
        if(!CollectionUtils.isNotEmpty(coordinates)){
            throw  new PropertyExcption(400,"附近没有房产");
        }
        List<House> houseList=new ArrayList<>();
        coordinates.stream().forEach(
                parm->{
                    House house=houseMapper.selectByPrimaryKey(Integer.valueOf(parm.getHid()));
                    houseList.add(house);
                }
        );
        return houseList;
    }


}
