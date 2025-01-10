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

    /**
     * 扫码登入 需要重做
     * @param id
     * @return
     */
    @GetMapping("/loginForScan")
    public R loginForScan(@RequestParam("id")Integer id){
        dyService.login(id);
        return R.ok();
    }

    /**
     * 抖音发布视频
     * @param id
     * @return
     */
    @GetMapping("/publishAVideo")
    public R publishAVideo(@RequestParam("id")Integer id){
        dyService.publishAVideo(id);
        return R.ok();
    }

    @GetMapping("/getHotTopicByDouyinId")
    public R getHotTopicByDouyinId(@RequestParam("id")Integer id){

        return R.ok();
    }

    /**
     * 获取热门视频
     * @return
     */
    @GetMapping("/getPopularVideos")
    public R getPopularVideos(){
        R r = dyService.getPopularVideos();
        return r;
    }

    /**
     * 流水化
     * @return
     */
    @GetMapping("/streamlining")
    public R streamlining(){
        return R.ok();
    }
}
