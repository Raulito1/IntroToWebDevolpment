/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raulalvarado
 */
public class RoleDaoDbImpl implements RoleDao {
    // inject dependency injection
    @Inject
    JdbcTemplate jdbcTemplate;
    
    // Create
    private static final String SQL_INSERT_ROLE
            = "insert into Role(role_name) values(?)";
    // Read
    private static final String SQL_SELECT_ALL_ROLES
            = "select * from role_name";
    private static final String SQL_SELECT_ROLE_BY_ID
            = "select * from Role where role_id = ?";
    private static final String SQL_SELECT_ROLE_BY_ROLE
            = "select * from Role where role_name = ?";
    // Update
    private static final String SQL_UPDATE_ROLE
            = "update Role set role_name = ? where role_id = ?";
    // Delete
    private static final String SQL_DELETE_ROLE
            = "delete from role where role_id = ?";
    // Delete role_id from join table 
    private static final String SQL_DELETE_USER_ROLE
            = "delete from user_role where role_id = ?";
    
    //=====Implementation of RoleDao========

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Role createRole(Role role) {
        jdbcTemplate.update(SQL_INSERT_ROLE,
                role.getName());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setRoleId(newId);
        
        return role;
        
    }

    @Override
    public Role getRoleById(int roleId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ROLE_BY_ID, new RoleMapper(),roleId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Role getRoleByRole(String role) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ROLE_BY_ROLE, new RoleMapper(),role);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public void updateRole(Role role) {
        jdbcTemplate.update(SQL_UPDATE_ROLE, role.getName(), role.getRoleId());
    }

    @Override
    public void deleteRole(int roleId) {
        jdbcTemplate.update(SQL_DELETE_ROLE, roleId);
        jdbcTemplate.update(SQL_DELETE_USER_ROLE, roleId);
    }
    
    //========= ROLEMAPPER =============
    public static final class RoleMapper implements RowMapper<Role> {
        @Override 
        public Role mapRow(ResultSet rs,int i) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getInt("role_id"));
            role.setName(rs.getString("role_name"));
            return role;
        }
    }
}
