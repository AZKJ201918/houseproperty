package com.azkj.houseproperty.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

//随机验证码
public class RandomNumberCode {

    public static String verCode(){
        Random random =new Random();
        return StringUtils.substring(String.valueOf(random.nextInt()*-10), 2, 6);
    }
    public static String randomNo(){
        Random random =new Random();
        return String.valueOf(Math.abs(random.nextInt()*-10));
    }
}
