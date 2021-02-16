package com.example.webproject.model.dao;

import com.example.webproject.exception.DaoException;
import com.example.webproject.model.entity.User;

public interface UserDao {
    User findUserByLogin(String login) throws DaoException;
    void addUser(User user, String password) throws DaoException;
    User findUserByLoginAndPassword(String login, String password) throws DaoException;

}
