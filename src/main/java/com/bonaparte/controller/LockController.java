package com.bonaparte.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangmingquan on 2018/7/10.
 */
@Api(value = "LockController", description = "Lock API")
@RestController
@RequestMapping("/lock")
public class LockController {
}
