package com.hx.pojo;


import java.io.Serializable;

public class UserFile implements Serializable {
    private Integer fid;
    private Integer uid;
    private String fileName;
    private String destPath;

    public UserFile() {
    }

    public UserFile(Integer fid, Integer uid, String fileName, String destPath) {
        this.fid = fid;
        this.uid = uid;
        this.fileName = fileName;
        this.destPath = destPath;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "fid=" + fid +
                ", uid=" + uid +
                ", fileName='" + fileName + '\'' +
                ", destPath='" + destPath + '\'' +
                '}';
    }
}
