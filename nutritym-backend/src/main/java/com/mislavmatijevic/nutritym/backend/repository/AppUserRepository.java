package com.mislavmatijevic.nutritym.backend.repository;

import com.mislavmatijevic.nutritym.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>
{
    AppUser getAppUserByEmail(String email);

    AppUser getAppUserByEmailAndPassword(String email, String password);
}
