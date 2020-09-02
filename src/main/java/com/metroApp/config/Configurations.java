package com.metroApp.config;

import com.metroApp.model.Metro;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class Configurations {
    private int fareIncrementStrategy;
    private int defaultBalance;
    private int minBalance;

    public int getMinBalance() {
        return minBalance;
    }

    public void setFareIncrementStrategy(int fareIncrementStrategy) {
        this.fareIncrementStrategy = fareIncrementStrategy;
    }

    public void setDefaultBalance(int defaultBalance) {
        this.defaultBalance = defaultBalance;
    }

    public void setMinBalance(int minBalance) {
        this.minBalance = minBalance;
    }

    public int getDefaultBalance() {
        return defaultBalance;
    }


    public int getFareIncrementStrategy() {
        return fareIncrementStrategy;
    }



}
