package com.example.accapplication.dto;

import com.example.accapplication.model.Users;

public class LoginResponseDto {
    private Users users;
    private String jwt;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(Users users, String jwt){
        this.users = users;
        this.jwt = jwt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
