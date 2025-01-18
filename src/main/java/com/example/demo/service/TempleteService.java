package com.example.demo.service;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.DouYinStreamliningBo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TempleteService {
    public R login(Integer id);

    public R publishAVideo(Integer id,String path);

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


    /**
     * 抖音流水化工作 watting 资源本地化开发完毕
     * @return
     */
    public R streamlining(@RequestBody List<DouYinStreamliningBo> list);
}

