package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/system")
public class SystemController {
    /**
     * 开启新的浏览器
     *
     * @return
     */
    @GetMapping("/StartWeb")
    public R startWeb(){
        HashMap<String, Object> map = WebDriverUtils.create();
        WebDriver obj = (WebDriver) map.get("obj");
        obj.get("https://example.com");
        return R.ok("start web for "+map.get("id"));
    }

    @GetMapping("/getWebDriveById")
    public R getWebServerById(@RequestParam("id")Integer id){
        WebDriver webDriver = WebDriverUtils.get(id);
        if (webDriver == null){
            return R.fail("no webDrive server");
        }
        return R.ok(webDriver);
    }

}
