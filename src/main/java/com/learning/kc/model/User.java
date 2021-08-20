package com.learning.kc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="Person")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue
    private Long usedId;

    @NotNull
    String userName;

    @NotNull
    String password;

    @NotNull
    @Email
    String email;

    public Long getUsedId() {
        return usedId;
    }

    public void setUsedId(Long usedId) {
        this.usedId = usedId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
