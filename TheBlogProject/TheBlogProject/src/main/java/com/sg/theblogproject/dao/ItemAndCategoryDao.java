/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.dao;

import com.sg.theblogproject.model.Category;
import com.sg.theblogproject.model.Item;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raulalvarado
 */
public interface ItemAndCategoryDao {
    //ITEM
    public Item addItem(Item item);
    
    public void deleteItem(int id);
    
    public void updateItem(Item item);
    
    public Item getItem(int id);
    
    public List<Item> getAllItems();
     
    public List<Item> getfeaturedItems();
    
    
    //CATEGORY
    public Category addCategory(Category category);
    
    public void deleteCategory(int id);
    
    public void updateCategory(Category category);
    
    public Category getCategory(int id);
    
    public List<Category> getAllCategories();
    
    public List<Category> getCategoriesByItemId(int itemId); 
    
}
