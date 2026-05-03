package com.jada.ecommerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;
    private double taxRate;

    //GETTERS
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public double getTaxRate() {
        return taxRate;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
