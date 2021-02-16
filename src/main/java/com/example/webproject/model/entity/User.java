package com.example.webproject.model.entity;

public class User {
    private String userLogin;
    private String userFirstName;
    private String userLastName;
    private String userCreditCard;
    private String userMailAddress;

    public User(String userLogin, String userFirstName, String userLastName, String userCreditCard, String userMailAddress) {
        this.userLogin = userLogin;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userCreditCard = userCreditCard;
        this.userMailAddress =userMailAddress;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserCreditCard() {
        return userCreditCard;
    }

    public void setUserCreditCard(String userCreditCard) {
        this.userCreditCard = userCreditCard;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }
}
