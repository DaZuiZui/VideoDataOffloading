package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.UploadBO;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件系统
 *
 */
@Service
public class FileServiceImpl implements FileService {

    private static Long userId = Long.valueOf(1);

    @Value("${config.FilePath}")
    private String filePath;

    /**
     * 上传文件
     * @return R 返回结果对象
     */
    @Override
    public R upload(MultipartFile file,String newName) {


        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            return R.fail("文件为空，上传失败");
        }

        // 检查文件保存路径是否存在，不存在则创建
        File directory = new File(filePath);
        if (!directory.exists() && !directory.mkdirs()) {
            return R.fail("文件目录创建失败");
        }

        // 获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 拼接完整文件路径
        String newFileName = newName + fileExtension;
        String fullPath = Paths.get(filePath+"\\"+userId, newFileName).toString();

        try {
            // 保存文件到目标路径
            Files.copy(file.getInputStream(), Paths.get(fullPath));
            return R.ok(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("文件上传失败: " + e.getMessage());
        }
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
