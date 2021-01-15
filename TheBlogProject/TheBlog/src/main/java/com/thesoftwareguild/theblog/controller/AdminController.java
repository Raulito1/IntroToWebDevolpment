/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.controller;

import com.thesoftwareguild.theblog.dao.RoleDao;
import com.thesoftwareguild.theblog.dao.UserDao;
import com.thesoftwareguild.theblog.model.Role;
import com.thesoftwareguild.theblog.model.User;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class AdminController {
    // Injectind UserDao dependency Injection
    @Inject
    UserDao user;
    // Role Dao
    @Inject 
    RoleDao role;
     //injecting Password Encoder
    @Inject
    PasswordEncoder encoder;
    
    @RequestMapping(value = "/admin", method  = RequestMethod.GET)
    public String displayAdminPage(Model model) {
        model.addAttribute("user", user.getAllUsers());
        return "admin";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(String username, String password) {
        User users = new User();
        users.setUserName(username);
        users.setPassword(encoder.encode(password));
        users.setEnabled(true);
        Set<Role> userRole = new HashSet<>();
        userRole.add(role.getRoleByRole("ROLE_USER"));
        
        users.setRoles(userRole);
        // adding to the user dao
        user.createUser(users);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(int id) {
        user.deleteUser(id);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUser(String[] roleIdList, Boolean enabled, int id) {
        User users  = user.getUserById(id);
        if (enabled != null) {
            users.setEnabled(enabled);
        } else {
            users.setEnabled(false);
        }
        
        Set<Role> roleList = new HashSet<>();
        for(String roleId : roleIdList) {
            Role roles = role.getRoleById(Integer.parseInt(roleId));
            roleList.add(roles);
        }
        users.setRoles(roleList);
        user.updateUser(users);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "editPassword", method = RequestMethod.POST)
    public String editPassword(int id, String password, String confirmPassword) {
        User users = user.getUserById(id);
        
        if (password.equals(confirmPassword)) {
            users.setPassword(password);
            user.updateUser(users);
            return "redirect:/admin";
        } else {
            return "redirect:editUser?id=" + id + "error=1";
        }
    }
}
