package com.pos.setting.mapper;



import org.mapstruct.Mapper;

import com.pos.dto.SettingSettingsDto;
import com.pos.setting.model.SettingSettings;

import java.util.Collection;


@Mapper
public interface SettingSettingsMapper {
    
    Collection<SettingSettingsDto> toSettingSettingsDto(Collection<SettingSettings> settings);

    Collection<SettingSettings> toSettingSettings(Collection<SettingSettingsDto> settings);

    SettingSettings toSettingSettings(SettingSettingsDto settingDto);

    SettingSettingsDto toSettingSettingsDto(SettingSettings setting);

}
