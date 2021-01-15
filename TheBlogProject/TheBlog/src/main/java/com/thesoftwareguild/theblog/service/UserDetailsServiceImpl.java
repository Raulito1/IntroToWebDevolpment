/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.service;

import com.thesoftwareguild.theblog.dao.UserDao;
import com.thesoftwareguild.theblog.model.Role;
import com.thesoftwareguild.theblog.model.User;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author raulalvarado
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Inject
    UserDao user;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User users = user.getUserByUsername(userName);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : users.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return new org.springframework.security.core.userdetails.User(users.getUserName(), users.getPassword(), grantedAuthorities);
    }
    
}
