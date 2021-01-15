/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Role;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface RoleDao {
    // Interface for Role
    public Role createRole(Role role);
    
    public Role getRoleById(int roleId);
    
    public Role getRoleByRole(String role);
    
    public List<Role> getAllRoles();
    
    public void updateRole(Role role);
    
    public void deleteRole(int roleId);
}
