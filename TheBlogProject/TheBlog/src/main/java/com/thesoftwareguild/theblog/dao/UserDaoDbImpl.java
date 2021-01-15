/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.dao.RoleDaoDbImpl.RoleMapper;
import com.thesoftwareguild.theblog.model.Role;
import com.thesoftwareguild.theblog.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raulalvarado
 */
@Repository
public class UserDaoDbImpl implements UserDao {
     //inject dependency injection for JDBC template
    @Inject
    JdbcTemplate jdbcTemplate;
    
    // =========== SQL STATEMENTS ===============
    // CREATE
    private static final String SQL_INSERT_USER
            = "insert into User (user_name, password, enabled) values (?, ?, ?)";
    // Create ===Inserting into join table user_role
    private static final String SQL_INSERT_USER_ROLE
            = "insert into User_Role (user_id, role_id) values (?,?)";
    // READ
    private static final String SQL_SELECT_USER_BY_ID
            = "select * from User where user_id = ?";
    private static final String SQL_SELECT_ALL_USERS
            = "select * from User";
    private static final String SQL_SELECT_USER_BY_USER_NAME
            = "select * from User where user_name = ?";
    private static final String SQL_SELECT_ROLE_FOR_USER
            = "select r.* from User_Role ur "
            + "join role r on ur.role_id = r.role_id "
            + "where ur.user_id = ?";
    // UPDATE
    private static final String SQL_UPDATE_USER
            = "update User set "
            + "user_name = ?, password = ?, enabled = ? "
            + "where user_id = ?";
    private static final String SQL_UPDATE_POST_AFTER_DELETE
            = "update post set "
            + "user_id = 1 "
            + "where user_id = ?";
    // DELETE
    private static final String SQL_DELETE_USER
            = "delete from User where user_id = ?";
    private static final String SQL_DELETE_USER_ROLE
            = "delete from User_Role where user_id = ?";
    

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                user.getUserName(),
                user.getPassword(),
                user.isEnabled());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        // add to the join table using helper method
        addUserRole(user);
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        jdbcTemplate.update(SQL_DELETE_USER_ROLE, userId);
        jdbcTemplate.update(SQL_UPDATE_POST_AFTER_DELETE, userId);
        jdbcTemplate.update(SQL_DELETE_USER, userId);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                    user.getUserName(),
                    user.getPassword(),
                    user.isEnabled(),
                    user.getUserId());
        jdbcTemplate.update(SQL_DELETE_USER_ROLE, user.getUserId());
        addUserRole(user);
    }

    @Override
    public User getUserById(int userId) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new UserMapper(), userId);
            user.setRoles(getRoleForUser(userId));
            user.setRoles(getRoleForUser(user.getUserId()));
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USER_NAME, new UserMapper(), username);
            user.setRoles(getRoleForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
        populateUser(user);
        return user;
    }
    
    //=============== HELPER METHODS ============
    private void addUserRole(User user) {
        user.getRoles().forEach(role -> {
            jdbcTemplate.update(SQL_INSERT_USER_ROLE, user.getUserId(), role.getRoleId());
        });
    }
    
    private Set<Role> getRoleForUser(int id) throws DataAccessException {
        Set<Role> role = new HashSet(jdbcTemplate.query(SQL_SELECT_ROLE_FOR_USER, new RoleMapper(), id));
        return role;
    }
    
    private void populateUser(List<User> users) {
        users.forEach(u -> {
            u.setRoles(getRoleForUser(u.getUserId()));
        });
    }
    
    // ============= USERMAPPER ===============
    public static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            return user;
        }
    }
}    
