package com.bonaparte.controller;

import com.alibaba.fastjson.JSON;
import com.bonaparte.bean.StatisticBean;
import com.bonaparte.util.ExcelUtils;
import io.swagger.annotations.*;
import org.apache.commons.collections4.CollectionUtils;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/8/29.
 * Excel的导入导出功能
 */
@RestController
@RequestMapping("/excelexport/")
public class ExcelExportController {

    private List<ExcelExportEntity> userEntityList = new ArrayList<ExcelExportEntity>(){{
        add(new ExcelExportEntity("姓名", "name"));
        add(new ExcelExportEntity("身份证号", "cardNum"));
        add(new ExcelExportEntity("手机号", "phone"));
        add(new ExcelExportEntity("籍贯", "birthPlace"));
        add(new ExcelExportEntity("出生日期", "birthDay"));
        add(new ExcelExportEntity("学历", "education"));
        add(new ExcelExportEntity("性别", "sex"));
    }};

    @ApiOperation(value = "用户信息导出",notes = "用户信息导出",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "用户信息导出", required = true, dataType = "String"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK")
    })
    @RequestMapping("/user")
    public void serviceOffExport(String data, HttpServletResponse response){
        List<Map<String,Object>> mapList = null;
        StatisticBean bean = JSON.parseObject(data,StatisticBean.class);
        bean.setPageSize(null);
        //查询结果存入mapList中
        if (!CollectionUtils.isEmpty(mapList)) {
            ExcelUtils.exportExcel(mapList, null, "用户数据", userEntityList, "用户数据.xlsx", response);
        }
    }
}
