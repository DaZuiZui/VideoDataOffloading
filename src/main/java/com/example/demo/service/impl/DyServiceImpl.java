package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.service.TempleteService;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class DyServiceImpl implements TempleteService {

    @Override
    public R login(Integer id) {
        WebDriver driver = WebDriverUtils.get(id);

        //获取当前会话的句柄
        String originalHandle = driver.getWindowHandle();

        // 打开新标签页
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // 切换到新标签页
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // 在新标签页中打开目标页面
        driver.get("https://creator.douyin.com/");
        System.out.println("已在新标签页中打开页面：https://creator.douyin.com/");
        return R.ok("scanf this code");
    }
}
