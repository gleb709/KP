package com.example.webproject.model.dao;

import com.example.webproject.exception.DaoException;
import com.example.webproject.model.entity.Account;
import com.example.webproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    void addAccount(Account account, String encryptedPassword) throws DaoException;
    Optional<Account> findAccountByLoginAndPassword(String login, String encryptedPassword) throws DaoException;
    Optional<Account> findAccountByLogin(String login) throws DaoException;
    Optional<Account> findAccountInfoByLogin(String login) throws DaoException;
    String checkAccountStatus(String login) throws DaoException;
    String checkAccountRole(String login) throws DaoException;
    void changeAccountStatus(String login, String status) throws DaoException;
    void changePasswordByEmail(String email, String encryptedPassword) throws DaoException;
    void changePasswordByLogin(String login, String encryptedPassword) throws DaoException;
    void deleteAccount(String login) throws DaoException;
    Optional<String> findLoginByEmail(String email) throws DaoException;
    List<String> findUsersByStatus(String status) throws DaoException;



    /*
    String checkAccountRole(String login) throws DaoException;
    String checkAccountStatus(String login) throws DaoException;

    void changeAccountStatus(String login, String status) throws DaoException;
    void changePasswordByEmail(String email, String encryptedPassword) throws DaoException;
    void changePasswordByLogin(String login, String encryptedPassword) throws DaoException;

    List<User> findAll(String login) throws DaoException;
    List<Account> findBlockedUsers() throws DaoException;

     */
}
