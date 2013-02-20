package com.petrpopov.yourtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * User: petrpopov
 * Date: 12.02.13
 * Time: 10:47
 */
@Configuration
public class InitBean {

    private final String filePath = "config.properties";

    public @Bean
    AppSettings getAppSettings()
    {
        AppSettings appSettings = new AppSettings();

        URL resource = getClass().getClassLoader().getResource(filePath);
        appSettings.loadConfig(resource.getFile());

        return appSettings;
    }
}
