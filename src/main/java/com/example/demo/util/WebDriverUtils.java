package com.example.demo.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverUtils  {
    public static HashMap<Integer,WebDriver> map = new HashMap<>();
    public static Integer id = 0;
    public static HashMap<String,Object> create(){
        id++;
        //todo 选择浏览器引擎

        System.setProperty("webdriver.gecko.driver", "/Users/yangyida/Downloads/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/Applications/Firefox.app/Contents/MacOS/firefox"));
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        // 使用已有的 Firefox 配置文件以复用会话
//        String userProfilePath = "/Users/yangyida/Library/Application Support/Firefox/Profiles/h5tw2jp0.default-release";
//        FirefoxProfile profile = new FirefoxProfile(new File(userProfilePath));
//        options.setProfile(profile);
        WebDriver driver =  new FirefoxDriver(options);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        map.put(id,driver);
//        System.err.println("浏览器已启动，加载配置文件：" + userProfilePath);
        HashMap<String,Object> res = new HashMap<>();
        res.put("id",id);
        res.put("obj",driver);
        return res;
    }

    public static WebDriver get(Integer id){
        WebDriver webDriver = map.get(id);
        if (webDriver == null) {
            return null;
        }

        return webDriver;
    }
}