package com.azkj.houseproperty.controller;

import com.azkj.houseproperty.common.constants.Constants;
import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.resp.ApiResult;
import com.azkj.houseproperty.entity.Journalism;
import com.azkj.houseproperty.entity.Particulars;
import com.azkj.houseproperty.entity.Slideshow;
import com.azkj.houseproperty.service.IndexService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;


    @ApiOperation(value = "轮播图",notes ="轮播图",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("SelectCarousel")
    public ApiResult SelectCarousel(){
        ApiResult<List<Slideshow>> result=new ApiResult();

        try {
            List<Slideshow> slideshowList=indexService.SelectCarousel();
            result.setData(slideshowList);
            result.setMessage("轮播图");
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



    @ApiOperation(value = "查看品牌",notes ="查看品牌",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("SelectParticulars")
    public ApiResult SelectParticulars(){
        ApiResult<Particulars> result=new ApiResult();

        try {
            Particulars particulars=indexService.SelectParticulars();
            result.setData(particulars);
            result.setMessage("轮播图");
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


    @ApiOperation(value = "查看新闻",notes ="查看新闻",httpMethod = "POST")
    @ApiImplicitParam
    @PostMapping("SelectJournalism")
    public ApiResult SelectJournalism(){
        ApiResult< List<Journalism>> result=new ApiResult();

        try {
            List<Journalism> journalisms=indexService.SelectJournalism();
            result.setData(journalisms);
            result.setMessage("轮播图");
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
