package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        // WebDriverManager 会自动为你下载与当前 Chrome 版本兼容的 ChromeDriver

//        WebDriver driver = new ChromeDriver();
        SpringApplication.run(Demo2Application.class, args);
        System.err.println("愿圣光保佑你，愿幸运女神永远站在你这边。");
        System.err.println("May the holy light bless you and may the goddess of luck always be on your side.");
    }

}
