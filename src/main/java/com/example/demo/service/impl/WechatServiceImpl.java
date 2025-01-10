package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.service.TempleteService;
import org.springframework.web.bind.annotation.RequestParam;

public class WechatServiceImpl implements TempleteService {
    @Override
    public R login(Integer id) {
        return null;
    }

    @Override
    public R getFarmerList(Integer id) {
        return null;
    }

    @Override
    public R publishAVideo(Integer id) {
        return null;
    }

    @Override
    public R getHotTopicByDouyinId(Integer id) {
        return null;
    }

    @Override
    public R getPopularVideos() {
        return null;
    }

    /**
     * 获取账号信息
     * @param id
     * @return
     */
    @Override
    public R getFarmerInfo(@RequestParam("id")Integer id){
        return R.ok();
    }
}
