/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.User;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface UserDao {
    //Interface for User
    public User createUser(User user);
    
    public void deleteUser(int userId);
    
    public void updateUser(User user);
    
    public User getUserById(int userId);
    
    public User getUserByUsername(String username);
    
    public List<User> getAllUsers();
}
