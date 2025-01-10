package com.example.demo.service;

import com.example.demo.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface SystemService {

    /**
     * 新的浏览器页面
     * @return
     */
    public R newTapOnwebDrive(Integer id);

}
