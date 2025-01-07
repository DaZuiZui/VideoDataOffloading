package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemController {
    /**
     * 开启新的浏览器
     *
     * @return
     */
    @GetMapping("StartWeb")
    public R startWeb(@RequestParam("id")Integer id){
        WebDriver webDriver = WebDriverUtils.create(id);
        webDriver.get("https://example.com");
        return R.ok("start web");
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
