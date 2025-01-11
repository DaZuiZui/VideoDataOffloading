package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.UploadBO;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
        String fullPath = Paths.get(filePath+"/"+userId, newFileName).toString();

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
        String fullPath = Paths.get(filePath + "/"+userId, filename).toString();
        File file = new File(fullPath);
        if (!file.exists()) {
            return R.fail("文件不存在");
        }

        try (InputStream inStream = new FileInputStream(file);
             OutputStream outStream = new FileOutputStream(filePath + "/" + UUID.randomUUID())) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            return R.ok("下载成功");

        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("下载失败");
        }
    }

    @Override
    public R getFileListByUserId() {

        String fullPath = Paths.get(filePath+"/"+userId).toString();
        File userFolder = new File(fullPath);

        if (!userFolder.exists() || !userFolder.isDirectory()) {
            return R.fail("用户文件夹不存在");
        }

        // 文件列表
        List<Map<String, Object>> fileList = new ArrayList<>();

        // 获取该用户文件夹下所有文件
        File[] files = userFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // 每个文件的详细信息
                    Map<String, Object> fileDetails = new HashMap();
                    fileDetails.put("file", file); // 文件对象
                    fileDetails.put("name", file.getName()); // 文件名
                    fileDetails.put("remark", "这是一个文件备注"); // 备注

                    // 将文件信息添加到列表中
                    fileList.add(fileDetails);
                }
            }
        }

        return R.ok(fileList);
    }
}
