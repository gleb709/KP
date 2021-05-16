package com.example.webproject.model.dao.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.model.connection.ConnectionPool;
import com.example.webproject.model.dao.AccountDao;
import com.example.webproject.model.dao.ColumnName;
import com.example.webproject.model.dao.DataBaseQuery;
import com.example.webproject.model.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl implements AccountDao {
    private static AccountDaoImpl instance;

    private AccountDaoImpl() {
    }

    public static AccountDaoImpl getInstance() {
        if (instance == null) {
            instance = new AccountDaoImpl();
        }
        return instance;
    }

    @Override
    public void addAccount(Account account, String encryptedPassword) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement(DataBaseQuery.ADD_ACCOUNT)){
                statement.setString(1, account.getLogin());
                statement.setString(2, encryptedPassword);
                statement.setString(3, account.getRole());
                statement.setString(4, account.getStatus());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding account to the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Optional<Account> findAccountByLoginAndPassword(String login, String encryptedPassword) throws DaoException {
        Optional<Account> account = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, encryptedPassword);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                account =Optional.of(new Account(login, result.getString(ColumnName.STATUS), result.getString(ColumnName.ROLE)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding account by login and password from database: ", e);
        }
        return account;
    }

    @Override
    public Optional<Account> findAccountByLogin(String login) throws DaoException {
        Optional<Account> account = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ACCOUNT_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                account = Optional.of(new Account(login, result.getString(RequestParameter.ACCOUNT_STATUS),
                        result.getString(RequestParameter.ACCOUNT_ROLE)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding account by login from database", e);
        }
        return account;
    }

    @Override
    public Optional<Account> findAccountInfoByLogin(String login) throws DaoException {
        Optional<Account> account = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ACCOUNT_INFO_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                account = Optional.of(new Account(login, result.getString(RequestParameter.ACCOUNT_STATUS),
                        result.getString(RequestParameter.ACCOUNT_ROLE)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding account info by login from database", e);
        }
        return account;
    }

    @Override
    public String checkAccountStatus(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        String status = null;

        try(Connection connection = connectionPool.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.CHECK_ACCOUNT_STATUS);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                status = result.getString(ColumnName.STATUS);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding account status from database", e);
        }
        return status;
    }

    @Override
    public String checkAccountRole(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        String status = null;

        try(Connection connection = connectionPool.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.CHECK_ACCOUNT_ROLE);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                status = result.getString(ColumnName.ROLE);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding account role from database", e);
        }
        return status;
    }

    @Override
    public void changeAccountStatus(String login, String status) throws DaoException {
        
    }

    @Override
    public void changePasswordByEmail(String email, String encryptedPassword) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try{
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(DataBaseQuery.CHANGE_PASSWORD_BY_EMAIL)) {
                statement.setString(1, encryptedPassword);
                statement.setString(2, email);
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while changing password in database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
               throw new DaoException("Error while changing password in database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void changePasswordByLogin(String login, String encryptedPassword) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try{
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(DataBaseQuery.CHANGE_PASSWORD_BY_LOGIN)) {
                statement.setString(1, encryptedPassword);
                statement.setString(2, login);
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new DaoException("Error while changing password in database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while changing password in database", e); //todo
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteAccount(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();

        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.DELETE_ACCOUNT)) {
                preparedStatement.setString(1, login);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while removing account from database", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwable) {
                throw new DaoException("Error while removing account from database", throwable);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<String> findLoginByEmail(String email) throws DaoException {
        Optional<String> login = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_LOGIN_BY_EMAIL);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                login = Optional.of(result.getString(ColumnName.LOGIN));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding login by email from database:", e);
        }
        return login;
    }

    @Override
    public List<String> findUsersByStatus(String status) throws DaoException {
        List<String> blockedUsers = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_USERS_BY_STATUS);
            statement.setString(1, status);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                blockedUsers.add(result.getString(ColumnName.LOGIN));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding login by email from database:", e);
        }
        return blockedUsers;
    }
}
