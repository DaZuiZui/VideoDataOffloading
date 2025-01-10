package com.example.demo.controller;

import com.example.demo.domain.DataCenter;
import com.example.demo.domain.R;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/system")
public class SystemController {
    /**
     * 开启新的浏览器
     *
     * 有一个性能的问题：一个业务对应一个浏览器，会不会太浪费性能了，我没有时间去处理性能问题了，以后再说
     *
     * type:
     *    1: 抖音中国
     *    2：Tiktok
     * name: 名字标识，不重要
     *
     *
     * 其实这里是我设计的一个失误，我应该在login的时候在做数据逻辑处理，
     * todo 更改
     * @return
     */
    @GetMapping("/StartWeb")
    public R startWeb(@RequestParam("type")Integer type,@RequestParam("name")String name){
        Long userId = 1L;
        HashMap<String, Object> map = WebDriverUtils.create();
        WebDriver obj = (WebDriver) map.get("obj");

        switch (type){
            //抖音中国
            case 1:
                //0. 查看是否为第一次，如果是第一次就初始化为0
                Object o = DataCenter.map.get(userId);
                if (o == null){
                    DataCenter.map.put(1L,new HashMap<String,Object>());
                }
                Long a = (Long) DataCenter.map.get(userId).get("douyinNumber");
                if (a == null){
                    a = 0L;
                }
                //1. 自增账号管理数量
                a++;
                DataCenter.map.get(userId).put("douyinNumber",a);
                //添加到管理账号的名单
                ArrayList<String> list = (ArrayList<String>) DataCenter.map.get(userId).get("douyinList");
                if (list == null ) {
                    list = new ArrayList<>();
                }

                list.add(name);
                DataCenter.map.get(userId).put("douyinList",list);

                break;

            //抖音国际
            case 2:
                break;
        }
        obj.get("https://example.com");
        return R.ok(map.get("id"));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/getWebDriveById")
    public R getWebServerById(@RequestParam("id")Integer id){
        WebDriver webDriver = WebDriverUtils.get(id);
        if (webDriver == null){
            return R.fail("no webDrive server");
        }
        return R.ok(webDriver);
    }

    /**
     * 退出浏览器 需维护
     * @param id
     * @return
     */
    @GetMapping("/quiteWebDrive")
    public R quiteWebDriver(@RequestParam("id")Integer id){
        WebDriverUtils.get(id).quit();
        return R.ok("web driver quited");
    }

}
