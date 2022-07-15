package com.mislavmatijevic.template.springsimpleauth;

import com.mislavmatijevic.template.springsimpleauth.dto.AppUserRegisterDto;
import com.mislavmatijevic.template.springsimpleauth.exceptions.UserAlreadyExistsException;
import com.mislavmatijevic.template.springsimpleauth.mapper.AppUserRegisterMapper;
import com.mislavmatijevic.template.springsimpleauth.model.AppUser;
import com.mislavmatijevic.template.springsimpleauth.services.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSimpleAuthApplicationTests
{
    @Autowired
    AppUserService userService;

    AppUserRegisterMapper userMapper = new AppUserRegisterMapper();

    @Test
    @Order(1)
    void registerAndAlreadyExistsTest()
    {
        AppUserRegisterDto registerUser = new AppUserRegisterDto();
        registerUser.setFirstName("Mislav");
        registerUser.setLastName("Matijević");
        registerUser.setEmail("mislav.mim@gmail.com");
        registerUser.setPassword("abcdefgh1");

        Optional<AppUser> found = userService.findById(userService.register(userMapper.map(registerUser)));
        assert found.isPresent();

        AppUser userWithAlreadyUsedEmail = new AppUser();
        userWithAlreadyUsedEmail.setEmail("mislav.mim@gmail.com");
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.register(userWithAlreadyUsedEmail));
    }
}
