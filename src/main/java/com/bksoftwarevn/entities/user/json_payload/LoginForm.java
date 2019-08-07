package com.bksoftwarevn.entities.user.json_payload;

import lombok.Data;

@Data
public class LoginForm {

    private String username;

    private String password;

    public LoginForm(){}

}
