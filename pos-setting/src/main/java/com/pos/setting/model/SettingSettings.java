package com.pos.setting.model;


import java.io.Serializable;

import lombok.Data;

@Data
public class SettingSettings implements Serializable {

    public SettingSettings(String app, String store, String addressOne, String addressTwo, String contact, String tax, String symbol, String percentage, String footer, String img) {
        this.app = app;
        this.store = store;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.contact = contact;
        this.tax = tax;
        this.symbol = symbol;
        this.percentage = percentage;
        this.footer = footer;
        this.img = img;
    }

    private String app;
    
    
    private String store;
    
    private String addressOne;
    
    private String addressTwo;
    
    private String contact;
    
    private String tax;
    
    private String symbol;
    
    private String percentage;
    
    private String footer;
    
    private String img;
    

    
}
