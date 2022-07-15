package com.mislavmatijevic.template.springsimpleauth.services;

import com.mislavmatijevic.template.springsimpleauth.model.AppUser;
import com.mislavmatijevic.template.springsimpleauth.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AppUserService
{
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository)
    {
        this.appUserRepository = appUserRepository;
    }

    public Optional<AppUser> findById(long id)
    {
        return appUserRepository.findById(id);
    }

    @Transactional
    public void save(AppUser appUser)
    {
        if (appUser != null)
            appUserRepository.saveAndFlush(appUser);
    }

    @Transactional
    public void delete(long id)
    {
        appUserRepository.deleteById(id);
    }
}
