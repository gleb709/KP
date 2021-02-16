package com.example.webproject.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum  ConnectionPool{
    INSTANCE;

    private static final String PROPERTIES_FILENAME = "property/dataBase.properties";
    private static final String DATABASE_URL = "url";
    private static final String DATABASE_DRIVER = "driver";
    private static final String DATABASE_LOGIN = "user";
    private static final String DATABASE_PASSWORD = "password";
    private static final int DEFAULT_POOL_SIZE = 32;

    private final BlockingQueue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> notAvailableConnections;

    private static final  Logger logger = LogManager.getLogger();

    ConnectionPool() {
        availableConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        notAvailableConnections = new ArrayDeque<>();
        initializePool();
    }

    private void initializePool(){
        Properties properties = new Properties();
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream inputStream = loader.getResourceAsStream(PROPERTIES_FILENAME);
        try {
            properties.load(inputStream);
            String sqlUrl =properties.getProperty(DATABASE_URL);
            String sqlDriver = properties.getProperty(DATABASE_DRIVER);
            Class.forName(sqlDriver);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                availableConnections.add(new ProxyConnection(DriverManager.getConnection(sqlUrl, properties.getProperty(DATABASE_LOGIN), properties.getProperty(DATABASE_PASSWORD))));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Connection pool initialize fail");
        }
    }

    public Connection getConnection(){
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = availableConnections.take();
            notAvailableConnections.add(proxyConnection);
        } catch (InterruptedException e) {
            logger.info("Connection is not established: " + e);
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection){
        if(connection instanceof ProxyConnection && notAvailableConnections.remove(connection)){
            availableConnections.offer((ProxyConnection) connection);
        }else{
            logger.info("Release Connection fail.");
        }
    }

    public void destroyPool(){
        for (int i =0; i < DEFAULT_POOL_SIZE; i++){
            try {
                availableConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.info("Pool destroying process fail: " + e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers(){
        while(DriverManager.getDrivers().hasMoreElements()){
            Driver driver = DriverManager.getDrivers().nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.info("driver deregister fail :" + e);
            }
        }
    }
}
