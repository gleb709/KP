package com.example.webproject.model.service.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.exception.EncryptionException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.dao.AccountDao;
import com.example.webproject.model.dao.TariffDao;
import com.example.webproject.model.dao.impl.AccountDaoImpl;
import com.example.webproject.model.dao.impl.TariffDaoImpl;
import com.example.webproject.model.dao.impl.UserDaoImpl;
import com.example.webproject.model.entity.*;
import com.example.webproject.model.service.UserService;
import com.example.webproject.util.Cryptographer;
import com.example.webproject.validator.UserValidator;

import javax.xml.soap.Detail;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

public class UserServiceImpl implements UserService {
    private static final String IS_ACTIVE = "isActive";
    private static final String IS_WAITING = "isWaiting";
    private static final String IS_BLOCKED = "isBlocked";
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String MAIN_ADMIN = "mainAdmin";

    @Override
    public List<String> signInUser(String login, String password) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = UserValidator.signInValidator(login, password);
        if (invalidData.isEmpty()) {
            try {
                Cryptographer cryptographer = new Cryptographer();
                String encryptedPassword = cryptographer.encrypt(password);
                Optional<Account> account = accountDao.findAccountByLoginAndPassword(login, encryptedPassword);
                if (!account.isPresent()) {
                    invalidData.add(RequestParameter.LOGIN);
                } else if (!account.get().getStatus().equals(IS_ACTIVE)) {
                    invalidData.add(RequestParameter.ACCOUNT_STATUS);
                }
            } catch (DaoException | EncryptionException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public Optional<Account> findAccountInfoByLogin(String login) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        Optional<Account> account = Optional.empty();
        if (UserValidator.loginValidator(login)) {
            try {
                account = accountDao.findAccountInfoByLogin(login);
            } catch (DaoException e) {
                throw new ServiceException();
            }
        }
        return account;
    }

    @Override
    public List<String> adminRegistration(Admin admin, Map<String, String> password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = UserValidator.signUpAdminValidator(admin, password);
        if (invalidData.isEmpty()) {
            try {
                if (accountDao.findAccountByLogin(admin.getAccount().getLogin()).isPresent()) {
                    invalidData.add(RequestParameter.LOGIN);
                } else {
                    Cryptographer cryptographer = new Cryptographer();
                    String encryptedPassword = cryptographer.encrypt(password.get(RequestParameter.PASSWORD));
                    accountDao.addAccount(admin.getAccount(), encryptedPassword);
                    userDao.addAdmin(admin);
                }
            } catch (DaoException | EncryptionException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<String> userRegistration(User user, Map<String, String> password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = UserValidator.signUpValidator(user, password);
        if (invalidData.isEmpty()) {
            try {
                if (accountDao.findAccountByLogin(user.getAccount().getLogin()).isPresent()) {
                    invalidData.add(RequestParameter.LOGIN);
                } else {
                    Cryptographer cryptographer = new Cryptographer();
                    String encryptedPassword = cryptographer.encrypt(password.get(RequestParameter.PASSWORD));
                    accountDao.addAccount(user.getAccount(), encryptedPassword);
                    userDao.addUser(user);
                }
            } catch (DaoException | EncryptionException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<String> accountActivation(String login, String key) throws ServiceException {
        List<String> incorrectData = UserValidator.accountActivationValidator(login, key);
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        if (incorrectData.isEmpty()) {
            try {
                if (accountDao.checkAccountStatus(login).equals(IS_WAITING)) {
                    accountDao.changeAccountStatus(login, IS_ACTIVE);
                } else {
                    incorrectData.add(IS_ACTIVE);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return incorrectData;
    }

    @Override
    public void changeTariff(String tariff, String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.changeTariff(tariff, login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Admin> findAdminInfoByLogin(String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        Optional<Admin> admin = Optional.empty();
        if (UserValidator.loginValidator(login)) {
            try {
                if (!accountDao.checkAccountRole(login).equals(USER)) {
                    admin = userDao.findAdminInfoByLogin(login);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return admin;
    }

    @Override
    public Optional<User> findUserInfoByLogin(String login) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        Optional<User> user = Optional.empty();
        if (UserValidator.loginValidator(login)) {
            try {
                if (accountDao.checkAccountRole(login).equals(USER)) {
                    user = userDao.findUserInfoByLogin(login);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return user;
    }

    @Override
    public Optional<User> findUserInfoByContractNumber(String contractNumber) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Optional<User> user = Optional.empty();
        if (UserValidator.contractNumberValidator(contractNumber)) {
            try {
                user = userDao.findUserInfoByContractNumber(contractNumber);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return user;
    }

    @Override
    public List<String> changeForgotPassword(String email, Map<String, String> password) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = UserValidator.changeForgotPasswordValidator(password);
        if (invalidData.isEmpty()) {
            try {
                Cryptographer cryptographer = new Cryptographer();
                String encryptedPassword = cryptographer.encrypt(password.get(RequestParameter.PASSWORD));
                accountDao.changePasswordByEmail(email, encryptedPassword);
            } catch (DaoException | EncryptionException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<String> changePassword(String login, Map<String, String> password) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = UserValidator.changePasswordValidator(password);
        if (invalidData.isEmpty()) {
            try {
                Cryptographer cryptographer = new Cryptographer();
                String encryptedPassword = cryptographer.encrypt(password.get(RequestParameter.NEW_PASSWORD));
                accountDao.changePasswordByLogin(login, encryptedPassword);
            } catch (DaoException | EncryptionException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<String> checkUserEmail(String email) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = new ArrayList<>();
        if (!UserValidator.emailValidator(email)) {
            invalidData.add(RequestParameter.EMAIL);
        } else {
            try {
                Optional<String> account = accountDao.findLoginByEmail(email);
                if (!account.isPresent()) {
                    invalidData.add(RequestParameter.EMAIL);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        List<User> users = new ArrayList<>();
        try {
            users = userDao.findAllUsersInfo();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Admin> findAllAdmins() throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        List<Admin> admins = new ArrayList<>();
        try {
            admins = userDao.findAllAdminsInfo();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void changeAccountStatus(String login, String status) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        try {
            accountDao.changeAccountStatus(login, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> deleteAccount(String login) throws ServiceException {
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        List<String> invalidData = new ArrayList<>();
        try {
            if (!accountDao.findAccountByLogin(login).isPresent()) {
                invalidData.add(RequestParameter.LOGIN);
            } else {
                accountDao.deleteAccount(login);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return invalidData;
    }
}