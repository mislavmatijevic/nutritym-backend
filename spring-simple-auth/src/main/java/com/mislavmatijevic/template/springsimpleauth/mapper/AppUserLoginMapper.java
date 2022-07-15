package com.mislavmatijevic.template.springsimpleauth.mapper;

import com.mislavmatijevic.template.springsimpleauth.dto.AppUserLoginDto;
import com.mislavmatijevic.template.springsimpleauth.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserLoginMapper implements GenericMapper<AppUserLoginDto, AppUser>
{
    @Override
    public AppUserLoginDto mapDto(final AppUser entity)
    {
        AppUserLoginDto appUserLoginDto = null;
        if (entity != null)
        {
            appUserLoginDto = new AppUserLoginDto();
            appUserLoginDto.setEmail(entity.getEmail());
            appUserLoginDto.setPassword(entity.getPassword());
        }
        return appUserLoginDto;
    }

    @Override
    public AppUser map(final AppUserLoginDto dto)
    {
        AppUser appUser = null;
        if (dto != null)
        {
            appUser = new AppUser();
            appUser.setEmail(dto.getEmail());
            appUser.setPassword(dto.getPassword());
        }
        return appUser;
    }
}
