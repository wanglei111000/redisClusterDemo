package com.test.redis.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Method;

@RestController(value = "/redis")
public class TestController {

    @ApiOperation(value="测试", notes="")
    @GetMapping(value = "/test01" )
//  @RequestMapping(value={""}, method= RequestMethod.GET)  //两种写法都可以
   public void test01(){
        System.out.println("============   测试swagger成功 ===================");
    }
}
