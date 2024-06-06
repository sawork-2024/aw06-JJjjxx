package com.pos.setting.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pos.setting.model.Setting;
import com.pos.setting.model.SettingSettings;

@Repository
public class SettingDB implements SettingRepository {
    
    
    private List<Setting> settings = null;

    SettingDB() {
        settings = new ArrayList<>();
        settings.add(new Setting("d36d", new SettingSettings("Standalone Point of Sale", "Store-Pos", "10086", "10087", "15968774896", "", "$", "10", "", "")));
    }

    @Override
    public List<Setting> allSettings() {
        return settings;
    }

}
