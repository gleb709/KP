package com.example.webproject.model.dao.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.model.connection.ConnectionPool;
import com.example.webproject.model.dao.UserDao;
import com.example.webproject.model.dao.query.DataBaseQuery;
import com.example.webproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = null;
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user = new User(result.getString(RequestParameter.LOGIN), result.getString(RequestParameter.FIRST_NAME),
                        result.getString(RequestParameter.LAST_NAME), result.getString(RequestParameter.CREDIT_CARD_NUMBER),
                            result.getString(RequestParameter.EMAIL));
            }
        } catch (SQLException e) {
            throw new DaoException("Find user fail: " + e);
        }
        return user;
    }

    @Override
    public void addUser(User user, String password) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_ACCOUNT);
                preparedStatement.setString(1, user.getUserLogin());
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, user.getUserFirstName());
                preparedStatement.setString(4, user.getUserLastName());
                preparedStatement.setString(5, user.getUserCreditCard());
                preparedStatement.setString(6, user.getUserMailAddress());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Add user fail: " + e);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user = new User(result.getString(RequestParameter.LOGIN), result.getString(RequestParameter.FIRST_NAME),
                        result.getString(RequestParameter.LAST_NAME), result.getString(RequestParameter.CREDIT_CARD_NUMBER),
                        result.getString(RequestParameter.EMAIL));
            }
        } catch (SQLException e) {
            throw new DaoException("Find user fail: " + e);
        }
        return user;
    }
}
