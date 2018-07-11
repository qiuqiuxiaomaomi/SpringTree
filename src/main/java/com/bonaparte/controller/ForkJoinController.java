package com.bonaparte.controller;

import com.bonaparte.service.ForkJoinService;
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
 * Created by yangmingquan on 2018/7/10.
 * Fork join框架，在必要的情况下，将一个大任务进行拆分成若干子任务，并将小任务加入到线程队列中
 * 再将一个个小任务的结果进行join汇总
 */
@Api(value = "ForkJoinController", description = "ForkJoin Api")
@RestController
@RequestMapping("/forjoin")
public class ForkJoinController {
    @Autowired
    private ForkJoinService forkJoinService;

    @ApiOperation(value = "basicOp",notes = "独立线程，lamda编程，Forkjoin框架执行时间比较",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "独立线程，lamda编程，Forkjoin框架执行时间比较")
    })
    @RequestMapping("/basicOp")
    public Object basicOp(){
        Map<String, Object> map = ControllerUtil.defaultSuccResult();
        forkJoinService.normalThread();
        forkJoinService.lamdaThread();
        forkJoinService.forkJoin();
        return map;
    }
}
