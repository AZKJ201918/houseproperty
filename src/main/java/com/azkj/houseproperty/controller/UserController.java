package com.azkj.houseproperty.controller;

import com.azkj.houseproperty.common.constants.Constants;
import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.resp.ApiResult;
import com.azkj.houseproperty.entity.Distribution;
import com.azkj.houseproperty.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @ApiOperation(value = "登陆",notes = "登陆",httpMethod ="POST")
    @ApiImplicitParam
    @PostMapping("/wxLogin")
    public ApiResult wxlogin(String code,String uuid,String encryptedData,String iv){
        ApiResult<String> result=new ApiResult<>();
        try {
           String uid=userService.wxlogin(code,uuid,encryptedData,iv);
            result.setData(uid);
            result.setMessage("登录成功");
        }catch (PropertyExcption e){
            result.setCode(e.getStatusCode());
            result.setMessage(e.getMessage());
        }catch (Exception e){
            log.error("SQL statement error or that place is empty"+e);
            result.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            result.setMessage("内部错误");
        }
        return  result;
    }

    @ApiOperation(value = "短信",notes = "短信",httpMethod ="POST")
    @PostMapping("note")
    public ApiResult Note(String phone){
        ApiResult result=new ApiResult();
        try {
            userService.Note(phone);
            result.setMessage("短信发送成功");
        }catch (PropertyExcption e){
            result.setCode(e.getStatusCode());
            result.setMessage(e.getMessage());
        }catch (Exception e){
            log.error("SQL statement error or that place is empty"+e);
            result.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            result.setMessage("内部错误");
        }
        return result;
    }


    @ApiOperation(value = "成为分销商",notes = "成为分销商",httpMethod ="POST")
    @PostMapping("distribution")
    public ApiResult InstrDistribution(Distribution distribution){
        ApiResult result=new ApiResult();
        try {
            userService.InstrDistribution(distribution);
            result.setMessage("成为分销商成功");
        }catch (PropertyExcption e){
            result.setCode(e.getStatusCode());
            result.setMessage(e.getMessage());
        }catch (Exception e){
            log.error("SQL statement error or that place is empty"+e);
            result.setCode(Constants.RESP_STATUS_INTERNAL_ERROR);
            result.setMessage("内部错误");
        }
        return result;
    }


}
