package com.mislavmatijevic.template.springsimpleauth.controllers;

import com.mislavmatijevic.template.springsimpleauth.dto.AppUserRegisterDto;
import com.mislavmatijevic.template.springsimpleauth.mapper.AppUserRegisterMapper;
import com.mislavmatijevic.template.springsimpleauth.responses.ApiResponse;
import com.mislavmatijevic.template.springsimpleauth.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AppUserController
{
    private final AppUserRegisterMapper userRegisterMapper = new AppUserRegisterMapper();
    private final AppUserService appUserService;

    @Autowired
    private AppUserController(AppUserService appUserService)
    {
        this.appUserService = appUserService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AppUserRegisterDto userRegistering)
    {
        long newUserId = appUserService.register(userRegisterMapper.map(userRegistering));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(String.valueOf(newUserId)));
    }
}
