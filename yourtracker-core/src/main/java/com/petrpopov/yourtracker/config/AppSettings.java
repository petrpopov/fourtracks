package com.petrpopov.yourtracker.config;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * User: petrpopov
 * Date: 28.01.13
 * Time: 13:48
 */

public class AppSettings {

    public void loadConfig(String fileConfigPath)
    {
        log().info("Loading config");

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(fileConfigPath));

            CLIENT_ID = properties.getProperty("client_id");
            CLIENT_SECRET = properties.getProperty("client_secret");
            CALLBACK_URL = properties.getProperty("callback_url");

            MONGODB_HOST = properties.getProperty("mongodb_host");
            MONGODB_DB = properties.getProperty("mongodb_db");

            log().info("Config loaded successfully ! :)");
        } catch (FileNotFoundException e) {
            log().error("Config file not found !");
            e.printStackTrace();
        } catch (IOException e) {
            log().error("Config file has IO problems !");
            e.printStackTrace();
        }

    }

    private Logger log()
    {
        return Logger.getLogger(AppSettings.class);
    }

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String CALLBACK_URL;
    public static String MONGODB_HOST;
    public static String MONGODB_DB;
}
