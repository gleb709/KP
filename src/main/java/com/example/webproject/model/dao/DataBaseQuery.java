package com.example.webproject.model.dao;

public class DataBaseQuery {
    private DataBaseQuery(){}

    public static final String FIND_ACCOUNT_INFO_BY_LOGIN = "SELECT status, role FROM account WHERE login = ?";
    public static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = "SELECT role, status From account WHERE login = ? AND password = ?";
    public static final String FIND_ACCOUNT_BY_LOGIN = "SELECT status, role FROM account WHERE login = ?";
    public static final String FIND_ADMIN_INFO_BY_LOGIN = "SELECT firstname, lastname, phonenumber, status, role, email FROM userinfo INNER JOIN account ON account.login = account_login WHERE login = ?";
    public static final String FIND_USER_INFO_BY_LOGIN = "SELECT login, contractnumber, firstname, lastname, phonenumber, email, role, status, paiddays, balance, tariffs_tariff FROM userinfo " +
            "INNER JOIN account ON account.login = account_login INNER JOIN tariffaccount ON tariffaccount.account_login = account.login WHERE login = ?";
    public static final String FIND_USER_INFO_BY_CONTRACT_NUMBER = "SELECT login, firstname, lastname, phonenumber, email, role, status, paiddays, balance, tariffs_tariff FROM userinfo " +
            "INNER JOIN account ON account.login = account_login INNER JOIN tariffaccount ON tariffaccount.account_login = account.login WHERE contractnumber = ?";
    public static final String FIND_TARIFF_ACCOUNT_INFO_BY_LOGIN = "SELECT balance, tariffs_tariff, paiddays, contractnumber FROM tariffaccount WHERE account_login = ?";
    public static final String FIND_LOGIN_BY_EMAIL = "SELECT login FROM account WHERE email = ?";
    public static final String FIND_ALL_USERS_INFO = "SELECT login, contractnumber, firstname, lastname, phonenumber, email, role, status, paiddays, balance, tariffs_tariff FROM userinfo " +
            "INNER JOIN account ON account.login = account_login INNER JOIN tariffaccount ON tariffaccount.account_login = account.login";
    public static final String FIND_ALL_ADMINS_INFO = "SELECT login, firstname, lastname, phonenumber, email, role, status FROM userinfo " +
            "INNER JOIN account ON account.login = account_login WHERE role = 'admin' ";
    public static final String FIND_ALL_TARIFFS = "SELECT tariff, price, info, discount, image FROM tariffs";
    public static final String FIND_TARIFF_BY_NAME="SELECT price, info, discount, image FROM tariffs WHERE tariff = ?";
    public static final String FIND_TARIFF_USERS = "SELECT account_login FROM tariffaccount WHERE tariffs_tariff = ?";
    public static final String FIND_TARIFF_INFO = "SELECT price, info, discount, image FROM tariffs WHERE tariff = ?";
    public static final String FIND_LAST_SUBSCRIPTION_FEE_INFO = "select date FROM operation WHERE operation_type = ? AND id = ( SELECT MAX(id) FROM operation WHERE operation_type = ? )";
    public static final String FIND_ALL_TARIFF_ACCOUNTS = "SELECT paiddays, balance, account_login, contractnumber, tariffs_tariff FROM tariffaccount";
    public static final String FIND_PAID_UP_DAYS_INFO = "SELECT account_login, paiddays FROM tariffaccount";
    public static final String FIND_USERS_BY_STATUS = "SELECT login FROM account WHERE status = ?";

    public static final String ADD_USER_TARIFF_ACCOUNT = "INSERT INTO tariffaccount (tariffs_tariff, paiddays, account_login, balance, contractnumber)" + "VALUES(?,?,?,?,?)";
    public static final String ADD_USER = "INSERT INTO userinfo (account_login, firstname, lastname, phonenumber, email)" + "VALUES (?,?,?,?,?)";
    public static final String ADD_ADMIN = "INSERT INTO userinfo (account_login, firstname, lastname, phonenumber, email)" + "VALUES (?,?,?,?,?)";
    public static final String ADD_ACCOUNT = "INSERT INTO account(login, password, role, status)" + "VALUES (?,?,?,?)";
    public static final String ADD_TARIFF = "INSERT INTO tariffs (tariff, price, info, discount, image)" + "VALUES(?,?,?,?,?)";
    public static final String ADD_PAYMENT = "INSERT INTO operation (operation_type, sum, date, account_login)" + "VALUES(?,?,?,?)";
    public static final String ADD_TRANSACTION = "INSERT INTO transaction (date,sum,creditCardNumber, login)" + "VALUES(?,?,?,?)";

    public static final String COLLECT_SUBSCRIPTION_FEE = "INSERT INTO operation (operation_type, sum, date, account_login)" + "VALUES(?,?,?,?)";

    public static final String CHECK_ACCOUNT_STATUS = "SELECT status FROM account WHERE login = ?";
    public static final String CHECK_ACCOUNT_ROLE = "SELECT role FROM account WHERE login = ?";

    public static final String CHANGE_PAID_UP_DAYS_COUNT = "UPDATE tariffaccount SET paiddays = ? WHERE account_login = ?";
    public static final String CHANGE_ACCOUNT_STATUS = "UPDATE account SET status = ? WHERE login = ?";
    public static final String CHANGE_BALANCE = "UPDATE tariffaccount SET balance = ? WHERE account_login = ?";
    public static final String CHANGE_TARIFF_ACCOUNT_INFO = "UPDATE tariffaccount SET balance = ?, paiddays = ? WHERE account_login = ?";
    public static final String CHANGE_PASSWORD_BY_EMAIL = "UPDATE account SET password = ? WHERE email = ?";
    public static final String CHANGE_PASSWORD_BY_LOGIN = "UPDATE account SET password = ? WHERE login = ?";
    public static final String CHANGE_TARIFF_INFO = "UPDATE tariffs SET price = ?, info = ?, discount = ?, image = ? WHERE tariff = ?";
    public static final String CHANGE_USER_TARIFF = "UPDATE tariffaccount SET paiddays = ?, tariffs_tariff = ? WHERE account_login = ?";

    public static final String DELETE_TARIFF = "DELETE FROM tariffs WHERE tariff = ?";
    public static final String DELETE_ACCOUNT = "DELETE FROM account INNER JOIN userinfo ON account_login = login WHERE login = ?";
}
