package com.mislavmatijevic.nutritym.backend.services;

import com.mislavmatijevic.nutritym.backend.exceptions.UserAlreadyExistsException;
import com.mislavmatijevic.nutritym.backend.exceptions.UserNotFoundException;
import com.mislavmatijevic.nutritym.backend.model.AppUser;
import com.mislavmatijevic.nutritym.backend.repository.AppUserRepository;
import com.mislavmatijevic.nutritym.backend.util.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    public long register(AppUser userRegistering) throws UserAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        if (appUserRepository.getAppUserByEmail(userRegistering.getEmail()) == null)
        {
            userRegistering.setPassword(PasswordHasher.hash(userRegistering.getPassword()));
            return this.save(userRegistering).getUserId();
        }
        else
        {
            throw new UserAlreadyExistsException();
        }
    }

    @Transactional
    public void login(final AppUser userLogging)
    {
        AppUser foundUser = appUserRepository.getAppUserByEmail(userLogging.getEmail());
        boolean isValidLogin = false;

        if (foundUser != null)
        {
            isValidLogin = PasswordHasher.checkHash(userLogging.getPassword(), foundUser.getPassword());
        }

        if (!isValidLogin)
        {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public AppUser save(AppUser appUser)
    {
        if (appUser != null)
            return appUserRepository.saveAndFlush(appUser);
        else
            return null;
    }

    @Transactional
    public void delete(long id)
    {
        appUserRepository.deleteById(id);
    }
}
