package com.example.demo.controller;

import com.example.demo.domain.R;
import com.example.demo.domain.bo.UploadBO;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    public R downFile(@RequestParam("filename") String filename, HttpServletResponse response) {
        // 调用服务下载文件
        return fileService.downFile(filename,response);
    }

    /**
     * 获取用户上传文件列表
     * @return
     */
    @GetMapping("/getFileListByUserId")
    public R getFileListByUserId() {
        // 调用服务根据userId查询文件列表
        return fileService.getFileListByUserId();
    }

    /**
     * 获取用户上传文件列表
     * @return
     */
    @PostMapping("/deleteFile")
    public R deleteFile(@RequestParam("filename") String filename) {
        // 调用服务根据userId查询文件列表
        return fileService.deleteFile(filename);
    }
}
