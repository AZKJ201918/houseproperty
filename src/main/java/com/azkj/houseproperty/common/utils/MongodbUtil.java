package com.azkj.houseproperty.common.utils;

import com.azkj.houseproperty.entity.Coordinates;
import com.azkj.houseproperty.entity.Piont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongodbUtil {

    @Autowired
    private MongoTemplate mongoTemplate;



    //查询单个房产的地理坐标
    public Coordinates SelectPiont(Integer hid){
        Query query=Query.query(Criteria.where("hid").is(hid.toString()));
        Coordinates coordinates=mongoTemplate.findOne(query,Coordinates.class);
        return coordinates;
    }
    //查找附近10公里的的房产
    public List<Coordinates> findthenear(Piont piont){
        Point point=new Point(piont.getLongitude(),piont.getLatitude());
        Sphere sphere = new Sphere(point, 6.2137119 / 3963.2);
        List<Coordinates> positions = mongoTemplate.find(new Query(Criteria.where("location.coordinates").within(sphere)), Coordinates.class);
        return positions;
    }


}
