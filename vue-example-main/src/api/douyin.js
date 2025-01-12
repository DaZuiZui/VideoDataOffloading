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
export function getFarmerInfo(data){
    return http({
        url: "/api/douyin/getFarmerInfo",
        method: "get",
        params: data
    })
}



/**
 * 开启新的浏览器会话开启新的账户
 * @param {*} data
 * @returns
 */
export function loginForScan(data){
    return http({
        url: "/api/douyin/loginForScan",
        method: "get",
        params: data
    })
}


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
export function getPopularVideos(turnOn){
    return http({
        url: "/api/douyin/getPopularVideos",
        method: "get",
        params: {
            turnOn: turnOn
        }
    })
}



/**
 * 推送视频
 */
export function publishAVideo(data){
    return http({
        url: "/api/douyin/publishAVideo",
        method: "get",
        params: data
    })
}

/**
 * 获取工作账号列表
 */
export function getFarmerList(){
    return http({
        url: "/api/douyin/getFarmerList?id=1",
        method: "get",
    })
}