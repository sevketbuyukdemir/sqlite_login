package com.sevketbuyukdemir.sqlite_login;

public class user {
    public String userName;
    public String userPassword;
    public String userMail;
    public int userLuckyNumber;

    public user(String userName, String userPassword, String userMail, int userLuckyNumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userLuckyNumber = userLuckyNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getUserLuckyNumber() {
        return userLuckyNumber;
    }

    public void setUserLuckyNumber(int userLuckyNumber) {
        this.userLuckyNumber = userLuckyNumber;
    }

    @Override
    public String toString() {
        return userName + '-' + userPassword + '-' + userMail + '-' + userLuckyNumber;
    }
}
