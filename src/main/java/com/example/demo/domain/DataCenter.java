package com.example.demo.domain;

import java.io.Serializable;
import java.util.*;

/**
 * @author Bryan Yang
 * 数据中心
 *
 * todo: 我暂时并不想把它进行持久化，先缓存吧，后面看项目的表现吧，如果可以我将考虑持久话。目前来说我只想最短的时间开发好这个产品
 */
public class DataCenter implements Serializable {
    /**
     * 当前用户ID里的数据：
     *
     *    todo: 把map名字换成userData
     *
     *    name: Name[String]             名字
     *    douyinNumber: Number[Intger]   抖音中国所管理的账号数量
     *    douyinList:   array[FarmerInfo.class] 抖音账号名字列表
     *    tiktokNumber: Number[Intger]   抖音国际所管理的账号数量
     *    blibliNumber: Number[Intger]   哔哩哔哩所管理的账号数量
     *
     */
    public static Map<Long, HashMap<String,Object>> map = new HashMap<>();

    /**
     * 热点数据
     * Map : {
     *     Integer: Number[Intger]
     *              0 抖音中国
     *              1 Tiktok
     *
     *
     *         get => Map: {
     *             times: Date[Date time] 时间 基准香港时间，以服务器为准
     *             list:  array[
     *                      title: String[String],         标题
     *                      hotnumber: String[hotnumber]   热度
     *                  ]              Hot数据
     *         }
     * }
     */
    public static Map<Integer, HashMap<String,Object>> HotData =new HashMap<>();

}
