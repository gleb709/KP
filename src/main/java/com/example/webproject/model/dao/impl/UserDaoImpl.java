package com.example.webproject.model.dao.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.model.connection.ConnectionPool;
import com.example.webproject.model.dao.ColumnName;
import com.example.webproject.model.dao.DataBaseQuery;
import com.example.webproject.model.dao.UserDao;
import com.example.webproject.model.entity.Account;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl instance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public void addUser(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_USER);
                preparedStatement.setString(1, user.getAccount().getLogin());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setString(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_USER_TARIFF_ACCOUNT);
                preparedStatement.setString(1, user.getTariffAccount().getTariff());
                preparedStatement.setInt(2, user.getTariffAccount().getPaidUpDays());
                preparedStatement.setString(3, user.getTariffAccount().getUserLogin());
                preparedStatement.setBigDecimal(4, user.getTariffAccount().getBalance());
                preparedStatement.setString(5, user.getTariffAccount().getContractNumber());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding user to the database", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding user to the database ", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void addAdmin(Admin admin) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_ADMIN);
                preparedStatement.setString(1, admin.getAccount().getLogin());
                preparedStatement.setString(2, admin.getFirstName());
                preparedStatement.setString(3, admin.getLastName());
                preparedStatement.setString(4, admin.getPhoneNumber());
                preparedStatement.setString(5, admin.getEmail());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding admin to the database", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding admin to the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void changeTariff(String tariff, String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.CHANGE_USER_TARIFF)) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, tariff);
                preparedStatement.setString(3, login);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while change paid days count in the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while change paid days count in the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Optional<Admin> findAdminInfoByLogin(String login) throws DaoException {
        Optional<Admin> admin = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ADMIN_INFO_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Account account = new Account(login, result.getString(ColumnName.STATUS),
                        result.getString(ColumnName.ROLE));
                admin = Optional.of(new Admin(result.getString(ColumnName.NAME), result.getString(ColumnName.LAST_NAME),
                        result.getString(ColumnName.PHONE_NUMBER), result.getString(ColumnName.EMAIL), account));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding admin to the database", e);
        }
        return admin;
    }

    @Override   //todo
    public Optional<User> findUserInfoByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_USER_INFO_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Account account = new Account(login, result.getString(ColumnName.STATUS), result.getString(ColumnName.ROLE));
                TariffAccount tariffAccount = new TariffAccount(login, result.getBigDecimal(ColumnName.BALANCE), result.getString(ColumnName.TARIFF),
                        result.getInt(ColumnName.PAID_DAYS), result.getString(ColumnName.CONTRACT_NUMBER));
                user = Optional.of(new User(result.getString(ColumnName.NAME), result.getString(ColumnName.LAST_NAME),
                        result.getString(ColumnName.PHONE_NUMBER), result.getString(ColumnName.EMAIL), account, tariffAccount));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user info from database", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserInfoByContractNumber(String contractNumber) throws DaoException {
        Optional<User> user = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_USER_INFO_BY_CONTRACT_NUMBER);
            statement.setString(1, contractNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Account account = new Account(result.getString(ColumnName.LOGIN), result.getString(ColumnName.STATUS), result.getString(ColumnName.ROLE));
                TariffAccount tariffAccount = new TariffAccount(result.getString(ColumnName.LOGIN), result.getBigDecimal(ColumnName.BALANCE), result.getString(ColumnName.TARIFF),
                        result.getInt(ColumnName.PAID_DAYS), contractNumber);
                user = Optional.of(new User(result.getString(ColumnName.NAME), result.getString(ColumnName.LAST_NAME),
                        result.getString(ColumnName.PHONE_NUMBER), result.getString(ColumnName.EMAIL), account, tariffAccount));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user info from database", e);
        }
        return user;
    }

    @Override
    public List<User> findAllUsersInfo() throws DaoException {
        List<User> userList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ALL_USERS_INFO);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Account account = new Account(result.getString(ColumnName.LOGIN), result.getString(ColumnName.STATUS),
                        ColumnName.ROLE);
                TariffAccount tariffAccount = new TariffAccount(result.getString(ColumnName.LOGIN), result.getBigDecimal(ColumnName.BALANCE),
                        result.getString(ColumnName.TARIFF), result.getInt(ColumnName.PAID_DAYS), result.getString(ColumnName.CONTRACT_NUMBER));
                User user = new User(result.getString(ColumnName.NAME), result.getString(ColumnName.LAST_NAME),
                        result.getString(ColumnName.PHONE_NUMBER), result.getString(ColumnName.EMAIL), account, tariffAccount);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user info from database", e);
        }
        return userList;
    }

    @Override
    public List<Admin> findAllAdminsInfo() throws DaoException {
        List<Admin> adminList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ALL_ADMINS_INFO);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Account account = new Account(result.getString(ColumnName.LOGIN), result.getString(ColumnName.STATUS),
                        ColumnName.ROLE);
                Admin admin = new Admin(result.getString(ColumnName.NAME), result.getString(ColumnName.LAST_NAME),
                        result.getString(ColumnName.PHONE_NUMBER), result.getString(ColumnName.EMAIL), account);
                adminList.add(admin);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding admin info from database", e);
        }
        return adminList;
    }
}