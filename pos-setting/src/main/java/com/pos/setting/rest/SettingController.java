package com.pos.setting.rest;

import com.pos.dto.SettingDto;
import com.pos.setting.mapper.SettingMapper;
import com.pos.setting.model.Setting;
import com.pos.setting.service.*;
import com.pos.api.SettingsApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
public class SettingController implements SettingsApi {
    private final SettingMapper settingMapper;
    private final SettingService settingService;

    public SettingController(SettingService settingService, SettingMapper settingMapper) {
        this.settingService = settingService;
        this.settingMapper = settingMapper;
    }

    @Override
    public ResponseEntity<List<SettingDto>> getSettings() {
        List<Setting> settings = this.settingService.settings();
        Collection<SettingDto> settingsCollection = settingMapper.toSettingsDto(settings);
        List<SettingDto> settingDtos = new ArrayList<>(settingsCollection);
        if (settingDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(settingDtos, HttpStatus.OK);
    }


}
