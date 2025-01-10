package com.example.demo.domain;

import java.io.Serializable;

public class FarmerInfo implements Serializable {
    private String username;      // 用户名
    private String id;      // 抖音号
    private String signature;     // 签名
    private int followingCount;   // 关注数
    private int fansCount;        // 粉丝数
    private int likeCount;        // 获赞数

    @Override
    public String toString() {
        return "FarmerInfo{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", signature='" + signature + '\'' +
                ", followingCount=" + followingCount +
                ", fansCount=" + fansCount +
                ", likeCount=" + likeCount +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public FarmerInfo() {
    }

    public FarmerInfo(String username, String id, String signature, int followingCount, int fansCount, int likeCount, String avatarUrl) {
        this.username = username;
        this.id = id;
        this.signature = signature;
        this.followingCount = followingCount;
        this.fansCount = fansCount;
        this.likeCount = likeCount;
        this.avatarUrl = avatarUrl;
    }

    private String avatarUrl;     // 用户头像 URL

}
