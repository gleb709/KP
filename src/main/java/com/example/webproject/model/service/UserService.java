package com.example.webproject.model.service;

import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.*;

import javax.xml.ws.Service;
import java.sql.SQLRecoverableException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<String> signInUser(String login, String password) throws ServiceException;
    Optional<Account> findAccountInfoByLogin(String login) throws ServiceException;
    List<String> adminRegistration(Admin admin, Map<String, String> password)throws ServiceException;
    List<String> userRegistration(User user, Map<String, String> password) throws ServiceException;
    List<String> accountActivation(String login, String key) throws ServiceException;
    void changeTariff(String tariff, String login) throws ServiceException;
    Optional<Admin> findAdminInfoByLogin(String login) throws ServiceException;
    Optional<User> findUserInfoByLogin(String login) throws ServiceException;
    Optional<User> findUserInfoByContractNumber(String contractNumber) throws ServiceException;
    List<String> changeForgotPassword(String email, Map<String, String> password) throws ServiceException;
    List<String> changePassword(String login, Map<String, String> password) throws ServiceException;
    List<String> checkUserEmail(String email) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    List<Admin> findAllAdmins() throws ServiceException;
    void changeAccountStatus(String login, String status) throws ServiceException;
    List<String> deleteAccount(String login) throws ServiceException;





    /*
    List<String> accountActivation(String login, String key) throws ServiceException;
    List<String> userRegistration(User user, Map<String, String> password)throws ServiceException;
    List<String> adminRegistration(Admin admin, Map<String, String> password)throws ServiceException;
    List<String> checkUserEmail(String email) throws ServiceException;
    List<String> changeForgotPassword(String email, Map<String, String> password) throws ServiceException;
    List<String> changePassword(String login, Map<String, String> password) throws ServiceException;
    //List<String> transaction(String login, Map<String, String> creditCard) throws ServiceException;
    User findUserInfoByLogin(String login) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    List<Account> findBlockedUsers() throws ServiceException;
    List<User> findDebtors() throws ServiceException;
    User findTariffAccountInfoByLogin(String login) throws ServiceException;
    User findAccountInfoByLogin(String login) throws ServiceException;
    User findAccountEmailByLogin(String login) throws ServiceException;
    //List<String> tariffPayment(String login, Payment paymentForm) throws ServiceException;

     */
}
