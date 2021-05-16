package com.example.webproject.model.dao;

public class DataBaseQueryOld {
    private DataBaseQueryOld(){}

    public static final String FIND_USER_BY_EMAIL = "SELECT login FROM userlist WHERE email = ?";
    public static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = "SELECT role, email, status From userlist WHERE login = ? AND password = ?";
    public static final String FIND_ACCOUNT_BY_LOGIN = "SELECT status, email, role FROM userlist WHERE login = ?";
    public static final String FIND_EMAIL_BY_LOGIN = "SELECT email FROM userlist WHERE login = ?";
    public static final String FIND_USER_TARIFF_ACCOUNT_BY_LOGIN = "SELECT balance, tariff, payment FROM usertariffaccount WHERE login = ?";
    public static final String FIND_TARIFF_BY_LOGIN = "SELECT tariff FROM useraccount WHERE login = ?";
    public static final String FIND_TARIFF_INFO_BY_NAME = "SELECT cost, sale,info FROM tariffs WHERE login = ?";
    public static final String FIND_USER_INFO_BY_LOGIN = "SELECT contractnumber, name, lastname, phonenumber FROM userinfo WHERE login = ?";
    public static final String FIND_USER_PAYMENT_HISTORY = "SELECT date, sum, paymenttype, currenttariff, previoustariff WHERE login = ?";
    public static final String FIND_ALL_USERS_INFO = "SELECT login, contractnumber, name, lastname, phonenumber FROM userinfo WHERE contractnumber IS NOT NULL";
    public static final String FIND_BLOCKED_USERS = "SELECT login, status FROM userlist WHERE status = ?";
    public static final String FIND_DEBTORS = "SELECT login FROM paymentAccount WHERE balance = ?";
    public static final String FIND_ACCOUNT_INFO_BY_LOGIN = "SELECT status, email, role FROM userlist WHERE login = ?";
    public static final String FIND_ADMIN_INFO_BY_LOGIN = "SELECT contractnumber, name, lastname, phonenumber, status, role, email FROM userinfo INNER JOIN userlist ON userlist.login = userinfo.login WHERE login = ?";

    public static final String ADD_USER_TARIFF_ACCOUNT = "INSERT INTO usertariffaccount (login, balance)" + "VALUES(?, ?)";
    public static final String ADD_USER = "INSERT INTO userinfo (login, contractnumber, name, lastname, phonenumber)" + "VALUES (?,?,?,?,?)";
    public static final String ADD_ADMIN = "INSERT INTO userinfo (login,  name, lastname, phonenumber)" + "VALUES (?,?,?,?)";
    public static final String ADD_ACCOUNT = "INSERT INTO userlist(login, password, role, email, status)" + "VALUES (?,?,?,?,?)";
    public static final String TRANSACTION = "INSERT INTO usertransaction (login, sum, date, creditcardnumber)" + "VALUES(?,?,?,?)";
    public static final String TARIFF_PAYMENT = "INSERT INTO userpayment (login, date, sum, paymenttype)" + "VALUES(?,?,?,?)";

    public static final String CHECK_ACCOUNT_STATUS = "SELECT status FROM userlist WHERE login = ?";
    public static final String CHECK_ACCOUNT_ROLE = "SELECT role FROM userlist WHERE login = ?";

    public static final String CHANGE_USER_BALANCE = "UPDATE usertariffaccount SET balance = ? WHERE login = ?";
    public static final String CHANGE_PASSWORD_BY_EMAIL = "UPDATE userlist SET password = ? WHERE email = ?";
    public static final String CHANGE_PASSWORD_BY_LOGIN = "UPDATE userlist SET password = ? WHERE login = ?";
    public static final String CHANGE_ACCOUNT_STATUS = "UPDATE userlist SET status = ? WHERE login = ?";
    public static final String CHANGE_BALANCE_AND_DAYS_COUNT = "UPDATE usertariffaccount SET payment = ? AND balance = ? WHERE login = ?";

}
