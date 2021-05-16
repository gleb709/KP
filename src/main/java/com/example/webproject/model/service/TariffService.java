package com.example.webproject.model.service;

import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.entity.Payment;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;

import java.util.List;
import java.util.Optional;

public interface TariffService {
    Optional<Tariff> findTariffInfo(String name) throws ServiceException;
    Optional<TariffAccount> findTariffAccountInfoByLogin(String login) throws ServiceException;
    Optional<String> lastSubscriptionFeeInfo() throws ServiceException;
    boolean collectionSubscriptionFee(String login) throws ServiceException;
    void addTransaction(String login, String sum, String creditCard) throws ServiceException;
    List<String> payment(String login, int dayCount) throws ServiceException;
    List<Tariff> findAllTariffs() throws ServiceException;
    List<String> addTariff(Tariff tariff) throws ServiceException;
    List<String> deleteTariff(String name) throws ServiceException;
    List<String> changeTariffInfo(Tariff tariff) throws ServiceException;
}