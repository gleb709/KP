package com.example.webproject.model.service.impl;

import com.example.webproject.controller.command.RequestParameter;
import com.example.webproject.exception.DaoException;
import com.example.webproject.exception.ServiceException;
import com.example.webproject.model.dao.impl.AccountDaoImpl;
import com.example.webproject.model.dao.impl.TariffDaoImpl;
import com.example.webproject.model.entity.Payment;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;
import com.example.webproject.model.service.TariffService;
import com.example.webproject.util.DateTransformer;
import com.example.webproject.validator.TariffValidator;
import com.example.webproject.validator.UserValidator;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


public class TariffServiceImpl implements TariffService {
    private static final int DAY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;
    private static final String IS_ACTIVE = "isActive";
    private static final String SUBSCRIPTION_FEE = "1";
    private static final String USER_PAYMENT = "2";
    @Override
    public Optional<Tariff> findTariffInfo(String name) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Optional<Tariff> tariff = Optional.empty();
        try {
            tariff = tariffDao.findTariffInfo(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariff;
    }

    @Override
    public Optional<TariffAccount> findTariffAccountInfoByLogin(String login) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Optional<TariffAccount> tariffAccount = Optional.empty();
        try {
            tariffAccount = tariffDao.findTariffAccountInfoByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariffAccount;
    }

    @Override
    public Optional<String> lastSubscriptionFeeInfo() throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Optional<String> lastSubscriptionFee = Optional.empty();
        try {
            lastSubscriptionFee = tariffDao.findLastSubscriptionFee(SUBSCRIPTION_FEE);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return lastSubscriptionFee;
    }

    @Override
    public boolean collectionSubscriptionFee(String login) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        AccountDaoImpl accountDao = AccountDaoImpl.getInstance();
        boolean success = true;
        try {
            Optional<String> lastSubscriptionFee = tariffDao.findLastSubscriptionFee(SUBSCRIPTION_FEE);
            Date presentDate = new Date();
            double sum = 0;
            if(lastSubscriptionFee.isPresent()){
                long difference = (presentDate.getTime() - DateTransformer.stringToDate(lastSubscriptionFee.get()).getTime());
                if(difference/DAY_IN_MILLISECONDS > 0){
                    Map<String, Integer> paidUpDays = tariffDao.findAllPaidUpDays();
                    List<String> activeUsers = accountDao.findUsersByStatus(IS_ACTIVE);
                    for (Map.Entry<String, Integer> entry: paidUpDays.entrySet()) {
                        if(activeUsers.contains(entry.getKey()) && entry.getValue()> -1){
                            entry.setValue(entry.getValue() - 1);
                            tariffDao.changePaidDays(entry.getKey(), entry.getValue());
                        }
                    }
                    String newDate = DateTransformer.dateToString(DateTransformer.addDays(DateTransformer.stringToDate(lastSubscriptionFee.get()),1));
                    tariffDao.collectionSubscriptionFee(login, newDate, BigDecimal.valueOf(sum), SUBSCRIPTION_FEE);
                }else {
                    success = false;
                }
            }else{
                Map<String, Integer> paidUpDays = tariffDao.findAllPaidUpDays();
                List<String> activeUsers = accountDao.findUsersByStatus(IS_ACTIVE);
                for (Map.Entry<String, Integer> entry: paidUpDays.entrySet()) {
                    if(activeUsers.contains(entry.getKey())){
                        entry.setValue(entry.getValue() - 1);
                        tariffDao.changePaidDays(entry.getKey(), entry.getValue());
                    }
                }
                tariffDao.collectionSubscriptionFee(login, DateTransformer.dateToString(presentDate), BigDecimal.valueOf(sum), SUBSCRIPTION_FEE);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void addTransaction(String login, String sum, String creditCard) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Date presentDate = new Date();
        Payment payment = new Payment(login, DateTransformer.dateToString(presentDate), BigDecimal.valueOf(Double.parseDouble(sum)), USER_PAYMENT);
        double userBalance = Double.parseDouble(sum);
        try{
            tariffDao.addTransaction(payment, creditCard);
            Optional<TariffAccount> tariffAccount = tariffDao.findTariffAccountInfoByLogin(login);
            if(tariffAccount.isPresent()){
                userBalance += tariffAccount.get().getBalance().doubleValue();
                tariffAccount.get().setBalance(BigDecimal.valueOf(userBalance));
                tariffDao.changeBalance(tariffAccount.get());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> payment(String login, int dayCount) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        List<String> invalidData = new ArrayList<>();
        try {
            Optional<TariffAccount> tariffAccount = tariffDao.findTariffAccountInfoByLogin(login);
            if(tariffAccount.isPresent()) {
                Optional<Tariff> tariff = tariffDao.findTariffByName(tariffAccount.get().getTariff());
                if (tariff.isPresent()) {
                    int maxPercent = 100;
                    double mathFormula = Math.round(tariff.get().getPrice() * (maxPercent - tariff.get().getDiscount()) * dayCount);
                    BigDecimal price = BigDecimal.valueOf(mathFormula / maxPercent);
                    if (tariffAccount.get().getBalance().doubleValue() > price.doubleValue()) {
                        Date date = new Date();
                        String presentDate = DateTransformer.dateToString(date);
                        Payment payment = new Payment(login, presentDate, price, USER_PAYMENT);
                        tariffDao.payment(payment);
                        BigDecimal newBalance = BigDecimal.valueOf(tariffAccount.get().getBalance().doubleValue() - price.doubleValue());
                        int newDayCount = tariffAccount.get().getPaidUpDays() + dayCount;
                        tariffAccount.get().setBalance(newBalance);
                        tariffAccount.get().setPaidUpDays(newDayCount);
                        tariffDao.changeTariffAccountInfo(tariffAccount.get());
                    } else {
                        invalidData.add(RequestParameter.SUM);
                    }
                } else {
                    invalidData.add(RequestParameter.INVALID);
                }
            }else {
                invalidData.add(RequestParameter.INVALID);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return invalidData;
    }

    @Override
    public List<Tariff> findAllTariffs() throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        List<Tariff> tariffs = new ArrayList<>();
        try {
            tariffs = tariffDao.findAllTariffs();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return tariffs;
    }

    @Override
    public List<String> addTariff(Tariff tariff) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        List<String> invalidData = TariffValidator.addTariffValidator(tariff);
        if(invalidData.isEmpty()) {
            try {
                if (tariffDao.findTariffByName(tariff.getName()).isPresent()) {
                    invalidData.add(RequestParameter.TARIFF);
                }else{
                    tariffDao.addTariff(tariff);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

    @Override
    public List<String> deleteTariff(String name) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        List<String> invalidData = new ArrayList<>();
        try {
            if (!tariffDao.findTariffByName(name).isPresent()) {
                invalidData.add(RequestParameter.TARIFF);
            } else if(!tariffDao.findTariffUsers(name).isEmpty()){
                invalidData.add(RequestParameter.USER_COUNT);
            } else{
                tariffDao.deleteTariff(name);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return invalidData;
    }

    @Override
    public List<String> changeTariffInfo(Tariff tariff) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        List<String> invalidData =  TariffValidator.addTariffValidator(tariff);
        if(invalidData.isEmpty()) {
            try {
                if (!tariffDao.findTariffByName(tariff.getName()).isPresent()) {
                    invalidData.add(RequestParameter.TARIFF);
                }else{
                    tariffDao.changeTariffInfo(tariff);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return invalidData;
    }

/*
    @Override
    public Tariff findUserTariffByLogin(String login) throws ServiceException {
        TariffDaoImpl tariffDao = TariffDaoImpl.getInstance();
        Tariff tariff = null;
        try {
            tariff = tariffDao.findTariffByLogin(login);
            if(tariff != null){
                tariff = tariffDao.findTariffInfoByName(tariff.getName());
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tariff;
    }

 */
}
