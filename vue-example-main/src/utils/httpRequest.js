/**
 * 杨易达
 * @email: y51288033@outlook.com
 *         y51288033@gmail.com
 */
import axios from 'axios'
 

/**
 * withCredentials: true: 表示允许发送跨域请求时携带凭据（比如 cookies、HTTP 认证信息等）。
 * 这是因为浏览器默认情况下不会发送跨域请求的凭据，但如果设置了 withCredentials: true，
 * 则会在请求中包含这些凭据。
 * withCredentials: false: 表示不携带跨域请求的凭据。浏览器会忽略发送请求时的凭据，适用于无需携带用户认证信息的情况。
 */
axios.defaults.withCredentials = true

const http = axios.create({
    timeout: 1000*30,
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    }
})
 
 /**
 * 请求拦截
 * @param {*} params 
 * @param {*} openDefultParams 
 * @returns 
 */
http.interceptors.request.use(config => {
    // // 根据请求路径动态设置 baseURL
    // if (config.url.startsWith('/api/hello')) {
    //     config.baseURL = 'http://192.168.0.109:8001';
    // } else if (config.url.startsWith('/api/hi')) {
    //     config.baseURL = 'http://192.168.0.109:8002';
    // }

    //设置请求头
    config.headers['Authorization'] = "ur token asjdb-qwneji-1231sad"
    config.headers['accept'] = '*/*'
    return config;
})


/**
 * 响应拦截
 * @param response 响应数据
 */
http.interceptors.response.use(response => {
    return response
})

/**
 * get请求参数处理
 * params 参数对象
 * openDefultParams 是否开启默认参数
 */
http.adornParams = (params = {}, openDefultParams = true) => {
    var defaults = {
        t: new Date().getTime()
    }
    return openDefultParams ? merge(defaults, params) : params
}

/**
 * post请求数据处理
 * @param {*} data 数据对象
 * @param {*} openDefultdata 是否开启默认数据?
 * @param {*} contentType 数据格式
 *  json: 'application/json; charset=utf-8'
 *  form: 'application/x-www-form-urlencoded; charset=utf-8'
 */
http.adornData = (data = {}, openDefultdata = true, contentType = 'json') => {
    var defaults = {
        t: new Date().getTime()
    }

    data = openDefultdata ? merge(defaults, data) : data
    return contentType === 'json' ? JSON.stringify(data) : qs.stringify(data)
}

// /**
//  * windPost请求
//  * @param {String} url [请求地址]
//  * @param {Object} params [请求携带参数]
//  */
// http.windPost = function (url, params) {
//     return new Promise((resolve, reject) => {
//         http.post(http.adornUrl(url), qs.stringify(params))
//             .then(res => {
//                 resolve(res.data)
//             })
//             .catch(error => {
//                 reject(error)
//             })
//     })
// }


/**
 * windGet请求
 * @param {String} url [请求地址]
 * @param {Object} params [请求携带参数]
 */
http.windGet = function (url, params) {
    return new Promise((resolve, reject) => {
        http.get(http.adornUrl(url), { params: params })
            .then(res => {
                resolve(res.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

/**
 * 上传图片
 */
http.upLoadPhoto = function (url, params, callback) {
    let config = {}
    if (callback !== null) {
        config = {
            onUploadProgress: function (progressEvent) {
                //属性lengthComputable主要表明总共需要完成的工作量和已经完成的工作是否可以被测量
                //如果lengthComputable为false，就获取不到progressEvent.total和progressEvent.loaded
                callback(progressEvent)
            }
        }
    }
    return new Promise((resolve, reject) => {
        http.post(http.adornUrl(url), http.adornParams(params), config)
            .then(res => {
                resolve(res.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}



export default http