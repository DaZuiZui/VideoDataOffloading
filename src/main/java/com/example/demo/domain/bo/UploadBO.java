package com.example.demo.domain.bo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


public class UploadBO implements Serializable {
    private MultipartFile file;
    private String newName;

    @Override
    public String toString() {
        return "UploadBO{" +
                "file=" + file +
                ", newName='" + newName + '\'' +
                '}';
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public UploadBO() {
    }

    public UploadBO(MultipartFile file, String newName) {
        this.file = file;
        this.newName = newName;
    }
}
