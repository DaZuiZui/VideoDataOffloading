package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.service.impl.DyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/douyin")
public class DouyinController {

    @Autowired
    private DyServiceImpl dyService;

    @GetMapping("/loginForScan")
    public R loginForScan(@RequestParam("id")Integer id){
        dyService.login(id);

        return R.ok();
    }
}
