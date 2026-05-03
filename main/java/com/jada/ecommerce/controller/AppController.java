package com.jada.ecommerce.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping
public class AppController {
    //FIELDS

    //APP NAME
    @Value("${app.name}")
    private String appName;

    //VERSION NUM
    @Value("${app.version}")
    private String appVersion;

    //ENDPOINT FOR VERSION INFO
    @GetMapping("/info")
    public String getAppInfo() {
        return appName + " - " + appVersion;
    }
}
