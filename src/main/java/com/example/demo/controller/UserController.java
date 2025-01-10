package com.example.demo.controller;

import com.example.demo.domain.DataCenter;
import com.example.demo.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/user/getInfo")
    public R getUserInfo(){
        return R.ok(DataCenter.map.get(1L));
    }
}
