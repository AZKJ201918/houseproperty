package com.azkj.houseproperty.entity;

import lombok.Data;


@Data
public class Piont {

    public Piont() {
    }
    public Piont(Double[] loc){
        this.longitude = loc[0];
        this.latitude = loc[1];
    }
    public Piont(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private Integer hid;

    private double longitude;

    private double latitude;
}
