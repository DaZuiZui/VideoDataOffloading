/**
 * 杨易达
 * @email: y51288033@outlook.com
 *         y51288033@gmail.com
 */
import http from "@/utils/httpRequest";

export function add(data){
    return http({
        url: "/api/hello/hello",
        method: "post",
        data: data
    })
}

/**
 * 获取热门话题
 * @returns 
 */
export function getUserInfoById(){
    return http({
        url: "/douyin/getPopularVideos",
        method: "get",
    })
}