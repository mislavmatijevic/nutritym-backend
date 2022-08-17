package com.mislavmatijevic.template.springsimpleauth.controllers;

import com.mislavmatijevic.template.springsimpleauth.dto.AppUserLoginDto;
import com.mislavmatijevic.template.springsimpleauth.dto.AppUserRegisterDto;
import com.mislavmatijevic.template.springsimpleauth.mapper.AppUserLoginMapper;
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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/auth")
public class AppUserController
{
    private final AppUserRegisterMapper userRegisterMapper = new AppUserRegisterMapper();
    private final AppUserLoginMapper userLoginMapper = new AppUserLoginMapper();
    private final AppUserService appUserService;

    @Autowired
    private AppUserController(AppUserService appUserService)
    {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AppUserRegisterDto userRegistering)
    {
        long newUserId = -1;

        try
        {
            newUserId = appUserService.register(userRegisterMapper.map(userRegistering));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
        }

        if (newUserId != -1)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(String.valueOf(newUserId)));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Could not create new user!"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody AppUserLoginDto userLoggingIn)
    {
        appUserService.login(userLoginMapper.map(userLoggingIn));
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("You're logged in!"));
    }
}
