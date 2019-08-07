package com.bksoftwarevn.entities.user.json_payload;

import lombok.Data;


@Data
public class RegisterForm {

    private String username;

    private String password;

    private String fullName;


}
