package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.DouYinStreamliningBo;
import com.example.demo.service.TempleteService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public R streamlining(List<DouYinStreamliningBo> list) {
        return null;
    }

    @Override
    public R publishAVideo(Integer id,String path) {
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

    @Override
    public R getFarmerInfo(Integer id, Integer PCID, String name) {
        return null;
    }


}
