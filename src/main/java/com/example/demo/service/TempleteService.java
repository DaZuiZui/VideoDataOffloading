package com.example.demo.service;

import com.example.demo.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TempleteService {
    public R login(Integer id);

    public R publishAVideo(Integer id);

    public R getHotTopicByDouyinId(Integer id);

    /**
     * 获取热门视频
     * @return
     */
    public R getPopularVideos();


    /**
     * 获取账号信息
     * @param id
     * @return
     */
    @GetMapping("/getFarmerInfo")
    public R getFarmerInfo(Integer id,Integer PCID,String name) ;

    /**
     * 获取工作账号列表
     * @param id
     * @return
     */
    @GetMapping("/getFarmerList")
    public R getFarmerList(@RequestParam("id")Integer id);
}

