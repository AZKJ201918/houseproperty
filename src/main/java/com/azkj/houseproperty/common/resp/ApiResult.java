package com.azkj.houseproperty.common.resp;


import com.azkj.houseproperty.common.constants.Constants;
import lombok.Data;


@Data
public class ApiResult<T> {

    private int code = Constants.RESP_STATUS_OK;

    private String message;

    private T data;


}
