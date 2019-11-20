package com.azkj.houseproperty.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;



@Data
public class Coordinates {

    private  String hid;

    private Object location;

    public Piont Coordinates(){
      Piont piont=new Piont();
     JSONObject jsonObject= (JSONObject) JSON.toJSON(this.location);
     JSONArray jsonArray=jsonObject.getJSONArray("coordinates");
     piont.setHid(Integer.valueOf(this.getHid()));
     piont.setLongitude(jsonArray.getDouble(0));
     piont.setLatitude(jsonArray.getDouble(1));
     return piont;
    }
}
