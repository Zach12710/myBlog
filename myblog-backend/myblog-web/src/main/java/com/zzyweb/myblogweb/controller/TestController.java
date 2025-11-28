package com.zzyweb.myblogweb.controller;

import com.zzyweb.myblogcommon.aspect.ApiOperationLog;
import com.zzyweb.myblogweb.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiyi
 *
 **/

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/user")
    @ApiOperationLog(description = "这是一个测试方法")
    public User test(@RequestBody User user) {
        return user;
    }
}
