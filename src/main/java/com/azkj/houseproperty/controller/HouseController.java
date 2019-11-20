package com.azkj.houseproperty.controller;
import com.azkj.houseproperty.common.constants.Constants;
import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.resp.ApiResult;
import com.azkj.houseproperty.entity.Details;
import com.azkj.houseproperty.entity.House;
import com.azkj.houseproperty.entity.Piont;
import com.azkj.houseproperty.service.HouseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class HouseController {

    @Autowired
    @Qualifier("houseServiceImpl")
    private HouseService houseService;




    @ApiOperation(value = "查看所有房产",notes ="查看房产",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("SelectHoust")
    public ApiResult SelectHoust(){
        ApiResult<List<House>> result=new ApiResult();
        try {
            List<House> houseList=houseService.SelectHoust();
            result.setData(houseList);
            result.setMessage("查看房产");
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

    @ApiOperation(value = "查看所有房产详情",notes ="查看房产详情",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("SelectParticulars")
    public ApiResult SelectParticulars(Integer id){
        ApiResult<List<Details>> result=new ApiResult();
        try {
            List<Details> details=houseService.SelectParticulars(id);
            result.setData(details);
            result.setMessage("查看房产");
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


    @ApiOperation(value = "查看附近10公里内的房产",notes ="查看附近10公里内的房产",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("CheckAttachment")
    public  ApiResult CheckAttachment(Piont piont){
        ApiResult<List<House>> result=new ApiResult();
        try {
            List<House> houseList=houseService.CheckAttachment(piont);
            result.setData(houseList);
            result.setMessage("查看附近房产");
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
