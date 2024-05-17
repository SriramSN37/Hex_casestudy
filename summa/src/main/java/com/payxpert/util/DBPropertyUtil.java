package com.payxpert.util;

import java.util.Properties;

public class DBPropertyUtil {
    public static Properties loadProperties(String serverName, String databaseName, String username, String password) {
        Properties properties = new Properties();
        
        properties.setProperty("serverName", serverName);
        properties.setProperty("databaseName", databaseName);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        return properties;
    }
}





