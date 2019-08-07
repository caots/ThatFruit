package com.bksoftwarevn.repository.user;

import com.bksoftwarevn.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAll();

    Role findByName(String name);

}