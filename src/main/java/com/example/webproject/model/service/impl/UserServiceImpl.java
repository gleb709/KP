package com.example.webproject.model.service.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.exception.EncryptionException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.dao.impl.UserDaoImpl;
import com.example.webproject.model.entity.User;
import com.example.webproject.model.service.UserService;
import com.example.webproject.util.Cryptographer;
import com.example.webproject.validator.UserValidator;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public List<String> userRegistration(User user, Map<String, String> password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<String> invalidData =  UserValidator.signUpValidator(user, password);
        if(invalidData.isEmpty()) {
            try {
                if (userDao.findUserByLogin(user.getUserLogin()) != null) {
                    invalidData.add(RequestParameter.LOGIN);
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        return invalidData;
    }

    @Override
    public void addUser(User user, String encryptedPassword) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            userDao.addUser(user, encryptedPassword);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<String> signInUser(String login, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<String> invalidData = UserValidator.signInValidator(login, password);
        if(invalidData.isEmpty()){
            try {
                Cryptographer cryptographer = new Cryptographer();
                String encodingPassword = cryptographer.encrypt(password);
                if( userDao.findUserByLoginAndPassword(login, encodingPassword) == null){
                    invalidData.add(RequestParameter.LOGIN);
                }
            } catch (DaoException | EncryptionException e) {
                e.printStackTrace();
            }
        }
        return invalidData;
    }
}
