package com.bonaparte.controller;

import com.bonaparte.service.SortAlgorithmService;
import com.bonaparte.util.ControllerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yangmingquan on 2018/7/24.
 */
@Api(value = "SortController", description = "")
@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortAlgorithmService sortAlgorithmService;

    @ApiOperation(value = "Sort排序操作接口",notes = "Sort排序操作接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/info")
    public Object sortInfo(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        try{
            int[] numbers = {1,2,5,6,8,9,5,3,5,6};
            sortAlgorithmService.quickSort(numbers, 0, 9);
            sortAlgorithmService.maopaoSort(numbers);
        }catch (Exception e){
            map = ControllerUtil.defaultSuccResult();
        }
        return map;
    }
}
