package com.example.webproject.model.dao;

import com.example.webproject.exception.DaoException;
import com.example.webproject.model.entity.Admin;
import com.example.webproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {
    void addUser(User user) throws DaoException;
    void addAdmin(Admin admin) throws DaoException;
    void changeTariff(String tariff, String login) throws DaoException;
    Optional<Admin> findAdminInfoByLogin(String login) throws DaoException;
    Optional<User> findUserInfoByLogin(String login) throws DaoException;
    Optional<User> findUserInfoByContractNumber(String contractNumber) throws DaoException;
    List<User> findAllUsersInfo() throws DaoException;
    List<Admin> findAllAdminsInfo() throws  DaoException;


    /*
    User findUserByLogin(String login) throws DaoException;
    void addUser(User user) throws DaoException;
    List<User> findAllUsersInfo() throws DaoException;
    List<User> findDebtors() throws DaoException;
    User findUserByEmail(String email) throws DaoException;
    void transaction(String login, Map<String, String> creditCard,  String date) throws DaoException;
    User findUserInfoByLogin(String login) throws DaoException;
    User findAccountEmailByLogin(String login) throws DaoException;
    User findTariffAccountInfoByLogin(String login) throws DaoException;
    List<Payment> findUserPaymentHistory(String login) throws DaoException;
    void changeUserBalance(String login, String newBalance) throws DaoException;
    void tariffPayment(String login, User user, Payment paymentForm) throws DaoException;
    void createUserTariffAccount(String login, String sum) throws DaoException;

     */
}
