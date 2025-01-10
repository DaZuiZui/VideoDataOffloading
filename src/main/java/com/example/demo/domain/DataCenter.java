package com.example.demo.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bryan Yang
 * 数据中心
 *
 * todo: 我暂时并不想把它进行持久化，先缓存吧，后面看项目的表现吧，如果可以我将考虑持久话。目前来说我只想最短的时间开发好这个产品
 */
public class DataCenter implements Serializable {
    /**
     * 用户ID：
     *
     *    name: Name[String]             名字
     *    douyinNumber: Number[Intger]   抖音中国所管理的账号数量
     *    douyinList:   array[ArrayList] 抖音账号名字列表
     *    tiktokNumber: Number[Intger]   抖音国际所管理的账号数量
     *    blibliNumber: Number[Intger]   哔哩哔哩所管理的账号数量
     *
     */
    public static Map<Long, HashMap<String,Object>> map = new HashMap<>();
}
