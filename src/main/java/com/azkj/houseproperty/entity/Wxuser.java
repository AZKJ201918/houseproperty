package com.azkj.houseproperty.entity;

public class Wxuser {
    private Integer id;

    private String uuid;

    private String openid;

    private String wxname;

    private String wximg;

    private String superiorid;

    private String unionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname == null ? null : wxname.trim();
    }

    public String getWximg() {
        return wximg;
    }

    public void setWximg(String wximg) {
        this.wximg = wximg == null ? null : wximg.trim();
    }

    public String getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid == null ? null : superiorid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }
}