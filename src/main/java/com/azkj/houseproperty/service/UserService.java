package com.azkj.houseproperty.service;

import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.entity.Distribution;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.apache.ibatis.annotations.Mapper;


public interface UserService {
    String wxlogin(String code, String uuid, String encryptedData, String iv) throws PropertyExcption, Base64DecodingException;

    void Note(String phone)throws PropertyExcption;

    void    InstrDistribution(Distribution distribution) throws PropertyExcption;
}
