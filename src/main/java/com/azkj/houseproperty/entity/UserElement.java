package com.azkj.houseproperty.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserElement {

    private String uuid;

    private String openid;


    /**
     * 转 map
     * @return
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uuid", this.uuid);
        map.put("openid", this.openid);

        return map;
    }

    /**
     * map转对象
     * @param map
     * @return
     */
    public static UserElement fromMap(Map<String, String> map) {
        UserElement ue = new UserElement();
        ue.setUuid(map.get("uuid"));
        ue.setOpenid(map.get("openid"));
        return ue;
    }
}
