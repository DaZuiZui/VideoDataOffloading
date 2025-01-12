package com.example.demo.service;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.UploadBO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public interface FileService {
    /**
     * 上传文件，上传文件后获得他的物理存储路径
     * @return
     */
    public R upload(MultipartFile file,String newName);

    /**
     * 去把文件下载下载  做一个下载的功能
     * @param filename
     * @return
     */
    public R downFile(String filename, HttpServletResponse response);

    /**
     * R.ok([{
     *     file:    文件
     *     name:    file name.
     *     remark:  文件的备注。
     * }])
     * @return
     */
    public R getFileListByUserId();

    /**
     * 删除文件
     * @return
     */
    public R deleteFile(String filename);

}
