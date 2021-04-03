package com.hx.pojo;


import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer uid;
    private String userName;
    private String password;
    private List<UserFile> userFileList;

    public User() {
    }

    public User(Integer uid, String userName, String password, List<UserFile> userFileList) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.userFileList = userFileList;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserFile> getUserFileList() {
        return userFileList;
    }

    public void setUserFileList(List<UserFile> userFileList) {
        this.userFileList = userFileList;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userFileList=" + userFileList +
                '}';
    }
}
