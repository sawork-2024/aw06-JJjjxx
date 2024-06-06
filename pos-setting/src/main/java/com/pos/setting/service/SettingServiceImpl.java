package com.pos.setting.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pos.setting.model.*;
import com.pos.setting.repository.SettingRepository;

import java.util.List;

@Service
@Slf4j // Lombok annotation to autocreate an Slf4j-based LoggerFactory as log
public class SettingServiceImpl implements SettingService {


    private SettingRepository settingRepository;


    public SettingServiceImpl(@Autowired SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public List<Setting> settings() {
        return settingRepository.allSettings();
    }
}
