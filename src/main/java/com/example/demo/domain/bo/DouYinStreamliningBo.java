package com.example.demo.domain.bo;

import java.io.Serializable;

public class DouYinStreamliningBo implements Serializable {
    private Integer pcid;
    private String  path;


    @Override
    public String toString() {
        return "DouYinStreamliningBo{" +
                "pcid=" + pcid +
                ", path='" + path + '\'' +
                '}';
    }

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DouYinStreamliningBo() {
    }

    public DouYinStreamliningBo(Integer pcid, String path) {
        this.pcid = pcid;
        this.path = path;
    }
}
