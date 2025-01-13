package com.razorpay.integration.entities;

import lombok.Data;


public class UserEntity {

    private String userId;
    private String userName;
    private String userEmailAddress;

    public UserEntity() {

    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmailAddress='" + userEmailAddress + '\'' +
                '}';
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public UserEntity(String userId, String userName, String userEmailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmailAddress = userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
