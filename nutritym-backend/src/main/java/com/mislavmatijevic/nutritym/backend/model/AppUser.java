package com.mislavmatijevic.nutritym.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "APP_USERS")
public class AppUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(final long userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String readablePassword)
    {
        this.password = readablePassword;
    }
}
