package com.mislavmatijevic.nutritym.backend.mapper;

import com.mislavmatijevic.nutritym.backend.dto.AppUserRegisterDto;
import com.mislavmatijevic.nutritym.backend.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserRegisterMapper implements GenericMapper<AppUserRegisterDto, AppUser>
{
    @Override
    public AppUserRegisterDto mapDto(final AppUser entity)
    {
        AppUserRegisterDto appUserRegisterDto = null;
        if (entity != null)
        {
            appUserRegisterDto = new AppUserRegisterDto();
            appUserRegisterDto.setFirstName(entity.getFirstName());
            appUserRegisterDto.setLastName(entity.getLastName());
            appUserRegisterDto.setEmail(entity.getEmail());
            appUserRegisterDto.setPassword(entity.getPassword());
        }
        return appUserRegisterDto;
    }

    @Override
    public AppUser map(final AppUserRegisterDto dto)
    {
        AppUser appUser = null;
        if (dto != null)
        {
            appUser = new AppUser();
            appUser.setFirstName(dto.getFirstName());
            appUser.setLastName(dto.getLastName());
            appUser.setEmail(dto.getEmail());
            appUser.setPassword(dto.getPassword());
        }
        return appUser;
    }
}
