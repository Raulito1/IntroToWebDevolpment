/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.controller.service;


import com.sg.theblogproject.dao.ItemAndCategoryDaoImpl;
import com.sg.theblogproject.model.Category;
import com.sg.theblogproject.model.Item;
import java.util.List;
import java.util.Map;
import com.sg.theblogproject.dao.ItemAndCategoryDao;

/**
 *
 * @author raulalvarado
 */
public class ItemAndCategoryDaoImplService implements ItemAndCategoryDao {
    
    ItemAndCategoryDao dao = new ItemAndCategoryDaoImpl();

    @Override
    public Item addItem(Item item) {
        item = dao.addItem(item);
        return item;
    }

    @Override
    public void deleteItem(int id) {
        try {
            dao.deleteItem(id);
        } catch (Exception ex) {
            System.out.println("No Item exists!");
        }
    }

    @Override
    public void updateItem(Item item) {
        try {
            dao.updateItem(item);
        } catch (Exception ex) {
            System.out.println("Bad information, can't complete");
        }
    }

    @Override
    public Item getItem(int id) {
        Item item;
        try {
            item = dao.getItem(id);
        } catch (Exception ex) {
            return null;
        }
        return item;
    }
    @Override
    public List<Item> getfeaturedItems(){
        List<Item> featuredItems;
        try{
            featuredItems =dao.getfeaturedItems();
        }catch (Exception ex){
            return null;
        }
        return featuredItems;
    }
    @Override
    public List<Item> getAllItems() {
        List<Item> items;
        try {
            items = dao.getAllItems();
        } catch (Exception ex) {
            return null;
        }
        return items;
    }

    @Override
    public Category addCategory(Category category) {
        // just a strg name dont need catch?
        Category cat = dao.addCategory(category);
        return cat;
    }

    @Override
    public void deleteCategory(int id) {
        try {
            dao.deleteCategory(id);
        } catch (Exception ex) {
            System.out.println("No category currently exists");
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            dao.updateCategory(category);
        } catch (Exception ex) {
            System.out.println("Invalid update fields");
        }
    }

    @Override
    public Category getCategory(int id) {
        Category cat;
        try {
            cat = dao.getCategory(id);
        } catch (Exception ex) {
            return null;
        }
        return cat;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> cats;
        try {
            cats = dao.getAllCategories();
        } catch (Exception ex) {
            return null;
        }
        return cats;
    }
    
    @Override
    public List<Category> getCategoriesByItemId(int itemId) {
        List<Category> cats;
        try {
            cats = dao.getCategoriesByItemId(itemId); 
        } catch (Exception ex) {
            return null;
        }
        return cats;
    }

    
}
