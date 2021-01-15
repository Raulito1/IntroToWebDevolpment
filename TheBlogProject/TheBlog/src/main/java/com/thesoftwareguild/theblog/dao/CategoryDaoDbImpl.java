/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Category;
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
public class CategoryDaoDbImpl implements CategoryDao {
    // Here im using dependancy Injection for my JdbcTemplate
    @Inject
    JdbcTemplate jdbcTemplate;
    // (CRUD)Create 
    private static final String SQL_INSERT_CATEGORY 
            = "INSERT INTO Category (category_name) values (?)";
    // (CRUD) Read
    private static final String SQL_SELECT_CATEGORY_BY_ID
            = "select * from category where category_id = ?";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from category";
    // (CRUD) Update
    private static final String SQL_UPDATE_CATEGORY
            = "update category set category_name = ? where category_id = ?";
    // (CRUD) Delete
    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where category_id = ?";
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category createCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getName());
        
        // query the database for the id that was jsut assigned to the new 
        // row in the database
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        // set the new id value on the category object and return it
        category.setCategoryId(newId);
        return category;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BY_ID, new CategoryMapper(), categoryId);
        } catch(EmptyResultDataAccessException ex) {
             // there were no results for the given contact id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                category.getName(),
                category.getCategoryId());
    }

    @Override
    public void deleteCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }
    
        public static final class CategoryMapper implements RowMapper<Category> {
            
            @Override
            public Category mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                return category;
            }
        }
}
