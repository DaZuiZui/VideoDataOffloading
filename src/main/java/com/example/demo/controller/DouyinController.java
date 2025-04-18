package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.DouYinStreamliningBo;
import com.example.demo.service.impl.DyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/douyin")
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
        R login = dyService.login(id);
        return R.ok(login);
    }

    /**
     * 处理手机短信验证
     * @param id
     * @return
     */
    @GetMapping("/phoneJugeCode")
    public R phoneJugeCode(@RequestParam("id")Integer id){
        R phoneJugeCode = dyService.phoneJugeCode(id);
        return R.ok(phoneJugeCode);
    }

    /**
     * 验证手机短信验证码是否正确
     * @param id
     * @return
     */
    @GetMapping("/phoneJugeTrueCode")
    public R phoneJugeTrueCode(@RequestParam("id")Integer id,@RequestParam("code")String code){
        R phoneJugeTrueCode = dyService.phoneJugeTrueCode(id,code);
        return R.ok(phoneJugeTrueCode);
    }

    /**
     * 抖音发布视频
     * @param id
     * @param path
     * @return
     */
    @GetMapping("/publishAVideo")
    public R publishAVideo(@RequestParam("id")Integer id,@RequestParam("path")String path){
        dyService.publishAVideo(id,path);
        return R.ok();
    }

    /**
     * 获取热门话题
     * todo: 缓存优化
     * @param id
     * @return
     */
    @GetMapping("/getHotTopicByDouyinId")
    public R getHotTopicByDouyinId(@RequestParam("id")Integer id){
        R hotTopicByDouyinId = dyService.getHotTopicByDouyinId(id);
        return hotTopicByDouyinId;
    }

    /**
     * 获取热门视频
     * todo: 缓存优化
     * https://www.iesdouyin.com/share/billboard/?id=0&utm_source=copy&utm_campaign=client_share&utm_medium=android&app=aweme
     * 目前是通关这个网站爬虫获取出来的，但是我不知道这个网站是否为抖音官方网站，大概率是的，
     * 如果有一天这个网站失效了那么就要参考 getHotTopicByDouyinId 来实现。
//     * @param turnOn  如果为0 那么就走智能模式，如果为1，那么就强制使用缓存
     * @return
     */
    @GetMapping("/getPopularVideos")
    public R getPopularVideos(@RequestParam("turnOn") Integer turnOn ){
        R r = dyService.getPopularVideos();
        return r;
    }


    /**
     * 抖音流水化工作 watting 资源本地化开发完毕
     * @return
     */
    @PostMapping("/streamlining")
    public R streamlining(@RequestBody List<DouYinStreamliningBo> list){
        System.err.println(list);
        return dyService.streamlining(list);
    }


    /**
     * 获取账号信息
     * @param id
     * @return
     */
    @GetMapping("/getFarmerInfo")
    public R getFarmerInfo(@RequestParam("id") Integer id,@RequestParam("PCID")Integer PCID,@RequestParam("name")String name) {
        R farmerInfo = dyService.getFarmerInfo(id,PCID,name);

        return farmerInfo;
    }

    /**
     * 获取工作账号列表
     * @param id
     * @return
     */
    @GetMapping("/getFarmerList")
    public R getFarmerList(@RequestParam("id")Integer id){
        R farmerList = dyService.getFarmerList(id);
        return farmerList;
    }



}
