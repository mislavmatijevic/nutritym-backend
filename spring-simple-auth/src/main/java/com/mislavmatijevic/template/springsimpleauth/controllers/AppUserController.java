package com.mislavmatijevic.template.springsimpleauth.controllers;

import com.mislavmatijevic.template.springsimpleauth.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AppUserController
{
    private final AppUserService appUserService;

    @Autowired
    private AppUserController(AppUserService appUserService)
    {
        this.appUserService = appUserService;
    }
}
