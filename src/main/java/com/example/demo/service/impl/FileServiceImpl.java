package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件系统
 *
 */
@Service
public class FileServiceImpl implements FileService {

    private static Long userId = Long.valueOf(1);

    @Value("config.FilePath")
    private static String FilePath;
    /**
     * 上传文件
     * @return
     */
    @Override
    public R upload(MultipartFile file,String remark) {
        return null;
    }


    @Override
    public R downFile(String filename) {
        return null;
    }

    @Override
    public R getFileListByUserId() {
        return null;
    }
}
