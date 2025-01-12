/**
 * 韩来凝
 * @email: 1920604577@qq.com
 *         18945342298@163.com
 */
import http from "@/utils/httpRequest";

/**
 * 文件上传
 */
export function upload(data) {
  return http({
    url: "/api/file/upload",
    method: "post",
    headers: {
      "Content-Type": "multipart/form-data", // 确保请求头是multipart/form-data
    },
    data: data,
  });
}

/**
 * 文件下载
 */
export function downFile(data) {
  return http({
    url: "/api/file/downFile",
    method: "post",
    data: data,
  });
}

/**
 * 视频资源管理
 */
export function getFileListByUserId() {
  return http({
    url: "/api/file/getFileListByUserId",
    method: "get",
  });
}
