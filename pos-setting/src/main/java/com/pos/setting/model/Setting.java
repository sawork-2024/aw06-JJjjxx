package com.pos.setting.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Setting implements Serializable {
    
    String id;
    SettingSettings settings;

    public Setting(String id, SettingSettings settings) {
        this.id = id;
        this.settings = settings;
    }


    
}
