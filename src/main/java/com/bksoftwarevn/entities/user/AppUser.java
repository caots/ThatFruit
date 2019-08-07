package com.bksoftwarevn.entities.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    private int age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    private String avatar;

    private int phone;

    private int gender;

    @Column(name = "accumulated_points")
    private int accumulatedPoints;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;


    private boolean status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> appRoles;

    public Collection<? extends GrantedAuthority> grantedAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        appRoles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getName())));
        return list;
    }

}