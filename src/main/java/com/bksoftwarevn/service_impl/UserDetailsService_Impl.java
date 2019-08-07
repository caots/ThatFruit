package com.bksoftwarevn.service_impl;

import com.bksoftwarevn.entities.user.Role;
import com.bksoftwarevn.entities.user.AppUser;
import com.bksoftwarevn.service_impl.user.AppUserService_Impl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsService_Impl implements UserDetailsService {

    private final AppUserService_Impl appUserService;

    public UserDetailsService_Impl(AppUserService_Impl appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByEmail(s);
        if (appUser == null) throw new UsernameNotFoundException(s);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Role> roles = appUser.getAppRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(appUser.getEmail(), appUser.getPassword(), grantedAuthorities);
    }
}
