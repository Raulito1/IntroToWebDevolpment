/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.dao;

import com.sg.theblogproject.model.Category;
import com.sg.theblogproject.model.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raulalvarado
 */
public class ItemAndCategoryDaoImpl implements ItemAndCategoryDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public static final String DELIMITER = ",";
    
    private static final class SqlQueries {

        
// ============= INSERT =================
        
        private static final String SQL_INSERT_NEW_CATEGORY
                = "INSERT INTO category "
                + "(categoryName) "
                + "VALUES (?)";

        
        //ADD imageTitle and filename
        private static final String SQL_INSERT_NEW_ITEM
                = "INSERT INTO item (itemName, description, featuredItem, imageTitle) "
                + "VALUES (?, ?, ?, ?)";


        private static final String SQL_INSERT_NEW_ITEM_CATEGORY
                = "INSERT INTO itemCategory "
                + "(FK_itemId, FK_categoryId) "
                + "VALUES (?, ?)";


// ================= READ ================
        
        private static final String SQL_GET_ALL_CATEGORIES
                = "SELECT * FROM category";

        private static final String SQL_GET_ALL_ITEMS
                = "SELECT * FROM item";

        private static final String SQL_GET_CATEGORY_BY_ID
                = "SELECT * FROM category "
                + "WHERE categoryId = ?";

        private static final String SQL_GET_ITEM_BY_ID
                = "SELECT * FROM item "
                + "WHERE itemId = ?";

        private static final String SQL_GET_AN_ITEM_CATEGORIES
                = "SELECT * FROM category "
                + "JOIN itemCategory on itemCategory.FK_categoryId = category.categoryId "
                + "WHERE itemCategory.FK_itemId = ?";

        private static final String SQL_GET_FEATURED_ITEMS
                = "SELECT * FROM item "
                + "WHERE featuredItem = 1";

        
// ============== UPDATE ===============        
        
        
        //Adding imageTitle and filename
        private static final String SQL_UPDATE_ITEM
                = "UPDATE item SET "
                + "itemName = ?, description = ?, featuredItem = ?, imageTitle = ? "
                + "WHERE itemId = ?";

        private static final String SQL_UPDATE_CATEGORY
                = "UPDATE category SET "
                + "categoryName = ? "
                + "WHERE categoryId = ?";


//===================== DELETE ===============
        
        private static final String SQL_DELETE_ITEM
                = "DELETE FROM item WHERE itemId = ?";

        private static final String SQL_DELETE_CATEGORY
                = "DELETE FROM category WHERE categoryId = ?";

        private static final String SQL_DELETE_CATEGORIES_TO_AN_ITEM
                = "DELETE FROM itemCategory "
                + "WHERE FK_itemId = ?";
        private static final String SQL_DELETE_CATEGORY_FROM_ALL_ITEMS
                = "DELETE FROM itemCategory "
                + "WHERE FK_categoryId = ?";
        private static final String SQL_DELETE_AN_ITEM_CATEGORY
                = "DELETE FROM itemCategory WHERE FK_itemId = ? AND FK_categoryId = ?";

    }

// ============== METHODS IMPLEMENTED =================    

    // methods start
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        System.err.println("WE HAVE ARE PUTTING IN A JDBCTEMPLATE");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item addItem(Item item) {
         //        name, description, value
        jdbcTemplate.update(SqlQueries.SQL_INSERT_NEW_ITEM,
                item.getItemName(),
                item.getDescription(),
                item.getFeaturedItem(),
                item.getImageTitle()
               
        );
        // I add the id to the category for if we want to use it after creating it, we have it handily.
        //item.setItemId(getLastInsertId());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        //need helper method to insert itemId and categoryId into itemCategory Bridge
        insertItemCategory(item);
        item.setItemId(id);

        return item;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteItem(int id) {
        //delete connections in bride table to the item
        jdbcTemplate.update(SqlQueries.SQL_DELETE_CATEGORIES_TO_AN_ITEM,
                id);
        //delete item
        jdbcTemplate.update(SqlQueries.SQL_DELETE_ITEM,
                id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateItem(Item item) {
        jdbcTemplate.update(SqlQueries.SQL_UPDATE_ITEM,
                // + "name = ?, description = ?, value = ?, "
                item.getItemName(),
                item.getDescription(),
                item.getFeaturedItem(),
                item.getImageTitle(),
                item.getItemId()
                );

        //delete connections in bride table to the item
        jdbcTemplate.update(SqlQueries.SQL_DELETE_CATEGORIES_TO_AN_ITEM,
                item.getItemId());

        insertItemCategory(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Item getItem(int id) {
        // queryForObject = get one (search by ID yeilds one)
        Item item = jdbcTemplate.queryForObject(SqlQueries.SQL_GET_ITEM_BY_ID,
                new ItemMapper(),
                id);
        
        
        List<Category> categories = jdbcTemplate.query(SqlQueries.SQL_GET_AN_ITEM_CATEGORIES,
                new CategoryMapper(), 
                id);  
        
        item.setCategories(categories);
        
        return item;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Item> getfeaturedItems() {
        // queryForObject = get one (search by ID yeilds one)
        List<Item> featuredItems = jdbcTemplate.query(SqlQueries.SQL_GET_FEATURED_ITEMS,
                new ItemMapper());

        return featuredItems;
    }

    @Override
    public List<Item> getAllItems() {
        // query = get list
        List<Item> items = jdbcTemplate.query(SqlQueries.SQL_GET_ALL_ITEMS,
                new ItemMapper());
        return items;
    }


//=========== CATEGORIES =================
    
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SqlQueries.SQL_INSERT_NEW_CATEGORY,
                category.getCategoryName()
        );
        // I add the id to the category for if we want to use it after creating it, we have it handily.
        category.setCategoryId(getLastInsertId());
        return category;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCategory(int id) {
        // pass in a category ID, then delete from bridge table anything that uses it.
        jdbcTemplate.update(SqlQueries.SQL_DELETE_CATEGORY_FROM_ALL_ITEMS,
                id);
        // not delete the non dependent-ed category
        jdbcTemplate.update(SqlQueries.SQL_DELETE_CATEGORY,
                id);
    }

    @Override
    public void updateCategory(Category category) {
        // pass in what I want the category to be (should set its ID to desired one for change)
        jdbcTemplate.update(SqlQueries.SQL_UPDATE_CATEGORY,
                category.getCategoryName(),
                category.getCategoryId());
    }

    @Override
    public Category getCategory(int id) {
        Category category = jdbcTemplate.queryForObject(SqlQueries.SQL_GET_CATEGORY_BY_ID,
                new CategoryMapper(),
                id);
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = jdbcTemplate.query(SqlQueries.SQL_GET_ALL_CATEGORIES,
                new CategoryMapper());
        return categories;
    }

    @Override
    public List<Category> getCategoriesByItemId(int itemId) {

        List<Category> categoryList = jdbcTemplate.
                query(SqlQueries.SQL_GET_AN_ITEM_CATEGORIES,
                        new CategoryMapper(),
                        itemId);

        return categoryList;
    }

// ============== HELPER METHODS =================

    private int getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    private void insertItemCategory(Item item) {
        final int itemId = item.getItemId();
        final List<Category> categories = item.getCategories();

        if (categories == null) {
            //do nothing 
        } else {
            //updating bridge table
            for (Category currentCategory : categories) {
                jdbcTemplate.update(SqlQueries.SQL_INSERT_NEW_ITEM_CATEGORY,
                        itemId,
                        currentCategory.getCategoryId());
            }
        }
    }


// ================ MAPPERS ========================    
    
    private static final class CategoryMapper implements RowMapper<Category> {

        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category cat = new Category();

            // this gets the base information from the dataTable, still need location info.
            cat.setCategoryName(rs.getString("categoryName"));
            cat.setCategoryId(rs.getInt("categoryId"));

            return cat;
        }
    }

    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            Item item = new Item();

            // this gets the base information from the dataTable, still need location info.
            item.setItemName(rs.getString("itemName"));
            item.setDescription(rs.getString("description"));
            item.setFeaturedItem(rs.getBoolean("featuredItem"));
            item.setImageTitle(rs.getString("imageTitle"));
            item.setItemId(rs.getInt("itemId"));

            return item;
        }
    }
}
