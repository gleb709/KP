package com.example.webproject.model.service;

import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.User;

import java.sql.SQLRecoverableException;
import java.util.List;
import java.util.Map;

public interface UserService {
    void addUser(User user, String password) throws ServiceException;
    List<String> userRegistration(User user, Map<String, String> password)throws ServiceException;
    List<String> signInUser(String login, String password) throws ServiceException;

}
