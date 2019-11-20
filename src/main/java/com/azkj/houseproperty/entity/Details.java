package com.azkj.houseproperty.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Details {
    private Integer id;

    private Integer hid;

    private String mainunit;

    private Date createtime;

    private Date delatime;

    private String equity;

    private String property;

    private String carport;

    private String decoration;

    private Integer afforest;

    private Integer volume;

    private Long cost;

    private String resulturl;

    private String uniturl;

    private String sceneryurl;

    private List<Design> designList;

    private Piont piont;

}