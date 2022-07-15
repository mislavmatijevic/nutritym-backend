package com.mislavmatijevic.template.springsimpleauth.repository;

import com.mislavmatijevic.template.springsimpleauth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>
{
    AppUser getAppUserByEmail(String email);

    AppUser getAppUserByEmailAndPassword(String email, String password);
}
