package com.example.webproject.model.dao.query;

public class DataBaseQuery {
    private DataBaseQuery(){}

    public static final String FIND_USER_BY_LOGIN = "SELECT login, firstName, lastName, creditCardNumber, email FROM userlist WHERE login = ?";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT login, firstName, lastName, creditCardNumber, email From userList WHERE login = ? AND password = ?";
    public static final String ADD_ACCOUNT = "INSERT INTO userList(login, password, firstName, lastName, creditCardNumber, email)" + "VALUES (?,?,?,?,?,?)";
}
