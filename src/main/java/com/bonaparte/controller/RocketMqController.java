package com.bonaparte.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "RocketMqController", description = "RocketMq API")
@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {
}
