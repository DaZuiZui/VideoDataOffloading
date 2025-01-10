/**
 * 杨易达
 * @email: y51288033@outlook.com
 *         y51288033@gmail.com
 */
import http from "@/utils/httpRequest";

/**
 * 开启新的浏览器会话开启新的账户
 * @param {*} data 
 * @returns 
 */
export function start(data){
    return http({
        url: "/api/system/StartWeb",
        method: "get",
        params: data
    })
}

/**
 * 获取热门话题
 * @returns 
 */
export function getPopularVideos(){
    return http({
        url: "/api/douyin/getPopularVideos",
        method: "get",
    })
}