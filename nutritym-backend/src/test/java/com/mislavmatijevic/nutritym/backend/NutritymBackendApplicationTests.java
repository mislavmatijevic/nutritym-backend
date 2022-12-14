package com.mislavmatijevic.nutritym.backend;

import com.mislavmatijevic.nutritym.backend.dto.AppUserRegisterDto;
import com.mislavmatijevic.nutritym.backend.exceptions.UserAlreadyExistsException;
import com.mislavmatijevic.nutritym.backend.exceptions.UserNotFoundException;
import com.mislavmatijevic.nutritym.backend.mapper.AppUserRegisterMapper;
import com.mislavmatijevic.nutritym.backend.model.AppUser;
import com.mislavmatijevic.nutritym.backend.services.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class NutritymBackendApplicationTests {
    // Ensures random users have different emails. That makes them unique between tests.
    private static int userCount = 0;

    @Autowired
    AppUserService userService;
    AppUserRegisterMapper userMapper = new AppUserRegisterMapper();

    @Test
    void registerTest() {
        AppUserRegisterDto registerUser = giveRandomUser();

        Optional<AppUser> found = Optional.empty();
        try {
            found = userService.findById(userService.register(userMapper.map(registerUser)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            Assertions.fail();
        }
        Assertions.assertNotNull(found);
    }

    @Test
    void alreadyExistsTest() {
        AppUserRegisterDto registerUser = giveRandomUser();
        try {
            userService.register(userMapper.map(registerUser));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        AppUser userWithAlreadyUsedEmail = new AppUser();
        userWithAlreadyUsedEmail.setEmail(registerUser.getEmail());

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.register(userWithAlreadyUsedEmail));
    }

    @Test
    void correctLoginTest() {
        AppUserRegisterDto registerUser = giveRandomUser();
        try {
            userService.register(userMapper.map(registerUser));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        AppUser loginUser = new AppUser();
        loginUser.setEmail(registerUser.getEmail());
        loginUser.setPassword(registerUser.getPassword());

        Assertions.assertDoesNotThrow(() -> userService.login(loginUser));
    }

    @Test
    void badLoginTest() {
        AppUser loginUser = new AppUser();
        loginUser.setEmail("thisEmailDoesNotExistInTheDB@tooBad.nope");
        loginUser.setPassword("iCanLiveWithThat");

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.login(loginUser));
    }

    AppUserRegisterDto giveRandomUser() {
        AppUserRegisterDto registerUser = new AppUserRegisterDto();
        registerUser.setFirstName("Mislav");
        registerUser.setLastName("Matijevi??");
        registerUser.setEmail("mislav.mim" + (++userCount) + "@gmail.com");
        registerUser.setPassword("abcdefgh1");
        return registerUser;
    }
}
