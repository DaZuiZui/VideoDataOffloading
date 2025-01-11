package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.UploadBO;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file,
                    @RequestParam("remark") String remark) {
        // 调用服务上传文件
        return fileService.upload(file, remark);
    }

    @PostMapping("/downFile")
    public R downFile(@RequestParam("filename") String filename) {
        // 调用服务下载文件
        return fileService.downFile(filename);
    }

    @GetMapping("/getFileListByUserId")
    public R getFileListByUserId() {
        // 调用服务根据userId查询文件列表
        return fileService.getFileListByUserId();
    }
}
