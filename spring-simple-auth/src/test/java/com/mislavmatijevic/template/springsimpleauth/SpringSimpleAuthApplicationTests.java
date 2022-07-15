package com.mislavmatijevic.template.springsimpleauth;

import com.mislavmatijevic.template.springsimpleauth.dto.AppUserRegisterDto;
import com.mislavmatijevic.template.springsimpleauth.exceptions.UserAlreadyExistsException;
import com.mislavmatijevic.template.springsimpleauth.exceptions.UserNotFoundException;
import com.mislavmatijevic.template.springsimpleauth.mapper.AppUserRegisterMapper;
import com.mislavmatijevic.template.springsimpleauth.model.AppUser;
import com.mislavmatijevic.template.springsimpleauth.services.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSimpleAuthApplicationTests
{
    // Ensures random users have different emails. That makes them unique between tests.
    private static int userCount = 0;

    @Autowired
    AppUserService userService;
    AppUserRegisterMapper userMapper = new AppUserRegisterMapper();

    @Test
    void registerTest()
    {
        AppUserRegisterDto registerUser = giveRandomUser();

        Optional<AppUser> found = userService.findById(userService.register(userMapper.map(registerUser)));
        Assertions.assertNotNull(found);
    }

    @Test
    void alreadyExistsTest()
    {
        AppUserRegisterDto registerUser = giveRandomUser();
        userService.register(userMapper.map(registerUser));

        AppUser userWithAlreadyUsedEmail = new AppUser();
        userWithAlreadyUsedEmail.setEmail(registerUser.getEmail());

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.register(userWithAlreadyUsedEmail));
    }

    @Test
    void correctLoginTest()
    {
        AppUserRegisterDto registerUser = giveRandomUser();
        userService.register(userMapper.map(registerUser));

        AppUser loginUser = new AppUser();
        loginUser.setEmail(registerUser.getEmail());
        loginUser.setPassword(registerUser.getPassword());

        Assertions.assertDoesNotThrow(() -> userService.login(loginUser));
    }

    @Test
    void badLoginTest()
    {
        AppUser loginUser = new AppUser();
        loginUser.setEmail("thisEmailDoesNotExistInTheDB@tooBad.nope");
        loginUser.setPassword("iCanLiveWithThat");

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.login(loginUser));
    }

    AppUserRegisterDto giveRandomUser()
    {
        AppUserRegisterDto registerUser = new AppUserRegisterDto();
        registerUser.setFirstName("Mislav");
        registerUser.setLastName("Matijević");
        registerUser.setEmail("mislav.mim" + (++userCount) + "@gmail.com");
        registerUser.setPassword("abcdefgh1");
        return registerUser;
    }
}
