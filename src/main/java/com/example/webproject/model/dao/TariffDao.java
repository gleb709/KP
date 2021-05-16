package com.example.webproject.model.dao;

import com.example.webproject.exception.DaoException;
import com.example.webproject.model.entity.Payment;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TariffDao {
    Optional<TariffAccount> findTariffAccountInfoByLogin(String login) throws DaoException;
    Optional<String> findLastSubscriptionFee(String operationType) throws DaoException;
    List<Tariff> findAllTariffs() throws DaoException;
    Optional<Tariff> findTariffByName(String tariff) throws DaoException;
    List<TariffAccount> findAllTariffAccounts() throws DaoException;
    void addTariff(Tariff tariff) throws DaoException;
    void deleteTariff(String name) throws DaoException;
    void payment(Payment payment) throws DaoException;
    void changeBalance(TariffAccount tariffAccount) throws DaoException;
    void changeTariffAccountInfo(TariffAccount tariffAccount) throws DaoException;
    void collectionSubscriptionFee(String login, String date, BigDecimal sum, String operationCode) throws DaoException;
    void changePaidDays(String login, int paidDays) throws DaoException;
    void addTransaction(Payment payment, String creditCard) throws DaoException;
    List<String> findTariffUsers(String name) throws DaoException;
    void changeTariffInfo(Tariff tariff) throws DaoException;
    Optional<Tariff> findTariffInfo(String name) throws DaoException;
    Map<String, Integer> findAllPaidUpDays() throws DaoException;
    //Tariff findTariffInfoByName(String tariffName) throws DaoException;
}
