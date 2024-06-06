package com.pos.setting.mapper;

import com.pos.dto.SettingDto;
import com.pos.setting.model.Setting;

import java.util.Collection;

import org.mapstruct.Mapper;

@Mapper
public interface SettingMapper {

    Collection<SettingDto> toSettingsDto(Collection<Setting> settings);

    Collection<Setting> toSettings(Collection<SettingDto> settings);

    Setting toSetting(SettingDto settingDto);

    SettingDto toSettingDto(Setting setting);
    
} 
