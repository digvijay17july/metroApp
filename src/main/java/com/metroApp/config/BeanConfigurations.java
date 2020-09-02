package com.metroApp.config;

import com.metroApp.model.Metro;
import com.metroApp.utils.MetroUtility;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfigurations {
    MetroUtility metroUtility;
    BeanConfigurations(MetroUtility metroUtility){
        this.metroUtility=metroUtility;
    }
    @Bean("metro")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Metro getMetro() {
        Metro metro=new Metro();
        metro.setStations(metroUtility.getStations());
        return metro;
    }
}
