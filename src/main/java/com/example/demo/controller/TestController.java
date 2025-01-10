package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/submit")
    public R submitTest() {
        HashMap<String, Object> map = WebDriverUtils.create();
        WebDriver webDriver = (WebDriver) map.get("obj");
        try {
            // 打开本地 HTML 文件，替换成你的本地路径
            String filePath = "file:///Users/yangyida/Downloads/litianyou/index1.html";
            webDriver.get(filePath);
            System.out.println("页面已加载");

            // 找到文件上传输入框
            WebElement fileInput = webDriver.findElement(By.xpath("//input[@type='file']"));
            System.out.println("找到文件上传输入框");

            // 使用 JavaScript 确保文件上传输入框可见
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].style.display = 'block';", fileInput);

            // 直接设置文件路径，绕过文件选择对话框
            String fileToUpload = "/Users/yangyida/Downloads/共情能力测评.png";
            fileInput.sendKeys(fileToUpload); // 模拟文件选择
            System.out.println("文件已选择：" + fileToUpload);

            // 点击提交按钮
            WebElement submitButton = webDriver.findElement(By.xpath("//button[contains(text(),'submit')]"));
            submitButton.click();
            System.out.println("点击了提交按钮");

        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("上传失败：" + e.getMessage());
        } finally {
            // 关闭浏览器
            webDriver.quit();
        }

        // 返回成功响应
        return R.ok("文件上传成功");
    }
}
