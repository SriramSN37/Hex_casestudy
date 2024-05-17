package com.payxpert.util;

import java.util.Properties;

public class DBConnUtil {
    public static String getConnectionString() {
       
        Properties properties = DBPropertyUtil.loadProperties("localhost", "payroll", "test", "summa");
        String serverName = properties.getProperty("serverName");
        String databaseName = properties.getProperty("databaseName");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        
        return "jdbc:mysql://" + serverName + ":3306/" + databaseName + "?user=" + username + "&password=" + password;
    }
}



