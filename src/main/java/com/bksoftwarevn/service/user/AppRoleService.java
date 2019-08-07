package com.bksoftwarevn.service.user;

import com.bksoftwarevn.entities.user.Role;

import java.util.List;

public interface AppRoleService {

    List<Role> findAll();

    Role findByName(String name);

}
