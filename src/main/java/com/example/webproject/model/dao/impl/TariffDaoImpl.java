package com.example.webproject.model.dao.impl;

import com.example.webproject.exception.DaoException;
import com.example.webproject.model.connection.ConnectionPool;
import com.example.webproject.model.dao.ColumnName;
import com.example.webproject.model.dao.DataBaseQuery;
import com.example.webproject.model.dao.TariffDao;
import com.example.webproject.model.entity.Payment;
import com.example.webproject.model.entity.Tariff;
import com.example.webproject.model.entity.TariffAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TariffDaoImpl implements TariffDao {

    private static TariffDaoImpl instance;

    private TariffDaoImpl() {
    }

    public static TariffDaoImpl getInstance() {
        if (instance == null) {
            instance = new TariffDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<TariffAccount> findTariffAccountInfoByLogin(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Optional<TariffAccount> tariffAccount = Optional.empty();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_ACCOUNT_INFO_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                tariffAccount = Optional.of(new TariffAccount(login, result.getBigDecimal(ColumnName.BALANCE), result.getString(ColumnName.TARIFF),
                        result.getInt(ColumnName.PAID_DAYS), result.getString(ColumnName.CONTRACT_NUMBER)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff account from database", e);
        }
        return tariffAccount;
    }

    @Override
    public Optional<String> findLastSubscriptionFee(String operationType) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Optional<String> lastSubscriptionFee = Optional.empty();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_LAST_SUBSCRIPTION_FEE_INFO);
            statement.setString(1, operationType);
            statement.setString(2, operationType);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                lastSubscriptionFee = Optional.of(result.getString(ColumnName.DATE));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff account from database", e);
        }
        return lastSubscriptionFee;
    }
/*
    @Override
    public Optional<String> findLastPaymentDate() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Optional<String> lastPaymentDate = Optional.empty();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_ACCOUNT_INFO_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                tariffAccount = Optional.of(new TariffAccount(login, result.getDouble(ColumnName.BALANCE), result.getString(ColumnName.TARIFF),
                        result.getInt(ColumnName.PAID_DAYS), result.getString(ColumnName.CONTRACT_NUMBER)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff account from database", e);
        }
        return tariffAccount;
    }

 */

    @Override
    public List<Tariff> findAllTariffs() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        List<Tariff> tariffs = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ALL_TARIFFS);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Tariff tariff = new Tariff(result.getString(ColumnName.TARIFF_NAME), result.getDouble(ColumnName.TARIFF_PRICE), result.getString(ColumnName.TARIFF_INFO),
                        result.getInt(ColumnName.TARIFF_DISCOUNT), result.getString(ColumnName.IMAGE_CODE));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariffs from database", e);
        }
        return tariffs;
    }

    @Override
    public Optional<Tariff> findTariffByName(String name) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Optional<Tariff> tariff = Optional.empty();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_BY_NAME);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                tariff = Optional.of(new Tariff(name, result.getDouble(ColumnName.TARIFF_PRICE), result.getString(ColumnName.TARIFF_INFO),
                        result.getDouble(ColumnName.TARIFF_DISCOUNT), result.getString(ColumnName.IMAGE_CODE)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff account from database", e);
        }
        return tariff;
    }

    @Override
    public List<TariffAccount> findAllTariffAccounts() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        List<TariffAccount> tariffAccounts = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_ALL_TARIFF_ACCOUNTS);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                TariffAccount tariff = new TariffAccount(result.getString(ColumnName.ACCOUNT_LOGIN), result.getBigDecimal(ColumnName.BALANCE), result.getString(ColumnName.TARIFF),
                        result.getInt(ColumnName.PAID_DAYS), result.getString(ColumnName.CONTRACT_NUMBER));
                tariffAccounts.add(tariff);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariffs from database", e);
        }
        return tariffAccounts;
    }

    @Override
    public void addTariff(Tariff tariff) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_TARIFF)) {
                preparedStatement.setString(1, tariff.getName());
                preparedStatement.setDouble(2, tariff.getPrice());
                preparedStatement.setString(3, tariff.getInfo());
                preparedStatement.setDouble(4, tariff.getDiscount());
                preparedStatement.setString(5, tariff.getImage());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding tariff to the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding tariff to the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteTariff(String name) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();

        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.DELETE_TARIFF)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while removing tariff from database", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwable) {
                throw new DaoException("Error while removing tariff from database", throwable);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void payment(Payment payment) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_PAYMENT)) {
                preparedStatement.setString(1, payment.getOperationType());
                preparedStatement.setBigDecimal(2, payment.getSum());
                preparedStatement.setString(3, payment.getDate());
                preparedStatement.setString(4, payment.getLogin());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding operation to the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding operation to the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void changeBalance(TariffAccount tariffAccount) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.CHANGE_BALANCE)) {
                preparedStatement.setBigDecimal(1, tariffAccount.getBalance());
                preparedStatement.setString(2, tariffAccount.getUserLogin());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while change paid days count in the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while change paid days count in the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void changeTariffAccountInfo(TariffAccount tariffAccount) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.CHANGE_TARIFF_ACCOUNT_INFO)) {
                preparedStatement.setBigDecimal(1, tariffAccount.getBalance());
                preparedStatement.setInt(2, tariffAccount.getPaidUpDays());
                preparedStatement.setString(3, tariffAccount.getUserLogin());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while change paid days count in the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while change paid days count in the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void collectionSubscriptionFee(String login, String date, BigDecimal sum, String operationCode) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.COLLECT_SUBSCRIPTION_FEE)) {
                preparedStatement.setString(1, operationCode);
                preparedStatement.setBigDecimal(2, sum);
                preparedStatement.setString(3, date);
                preparedStatement.setString(4, login);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while collect subscription fee from the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while collect subscription fee from the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void changePaidDays(String login, int paidDays) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.CHANGE_PAID_UP_DAYS_COUNT)) {
                preparedStatement.setInt(1, paidDays);
                preparedStatement.setString(2, login);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while change paid days count in the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while change paid days count in the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void addTransaction(Payment payment, String creditCard) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.ADD_TRANSACTION);
                preparedStatement.setString(1, payment.getDate());
                preparedStatement.setBigDecimal(2,payment.getSum());
                preparedStatement.setString(3, creditCard);
                preparedStatement.setString(4, payment.getLogin());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding transaction to the database", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding user to the database ", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public List<String> findTariffUsers(String name) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        List<String> users = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_USERS);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String userLogin = result.getString(ColumnName.ACCOUNT_LOGIN);
                users.add(userLogin);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding users from database", e);
        }
        return users;
    }

    @Override
    public void changeTariffInfo(Tariff tariff) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DataBaseQuery.CHANGE_TARIFF_INFO)) {
                preparedStatement.setDouble(1, tariff.getPrice());
                preparedStatement.setString(2, tariff.getInfo());
                preparedStatement.setDouble(3, tariff.getDiscount());
                preparedStatement.setString(4, tariff.getImage());
                preparedStatement.setString(5, tariff.getName());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding tariff to the database", e);
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DaoException("Error while adding tariff to the database", e);
            }
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Optional<Tariff> findTariffInfo(String name) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Optional<Tariff> tariff = Optional.empty();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_INFO);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                tariff = Optional.of(new Tariff(name, result.getDouble(ColumnName.TARIFF_PRICE), result.getString(ColumnName.TARIFF_INFO),
                        result.getDouble(ColumnName.TARIFF_DISCOUNT), result.getString(ColumnName.IMAGE_CODE)));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff info from database", e);
        }
        return tariff;
    }

    @Override
    public Map<String, Integer> findAllPaidUpDays() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Map<String, Integer> paidDaysMap = new HashMap<>();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_PAID_UP_DAYS_INFO);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                paidDaysMap.put(result.getString(ColumnName.ACCOUNT_LOGIN), result.getInt(ColumnName.PAID_DAYS));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding tariff info from database", e);
        }
        return paidDaysMap;
    }


    /*

    @Override
    public Tariff findTariffByLogin(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Tariff tariff = null;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_BY_LOGIN);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                tariff = new Tariff();
                tariff.setName(result.getString("tariff"));
            }
        } catch (SQLException e) {
            throw new DaoException("Find user fail:", e);
        }
        return tariff;
    }

    @Override
    public Tariff findTariffInfoByName(String tariffName) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        Tariff tariff = null;
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DataBaseQuery.FIND_TARIFF_INFO_BY_NAME);
            statement.setString(1, tariffName);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                tariff = new Tariff(tariffName, result.getString("cost"), result.getString("info"),
                        result.getString("sale"));
            }
        } catch (SQLException e) {
            throw new DaoException("Find user fail:", e);
        }
        return tariff;
    }

     */
}
