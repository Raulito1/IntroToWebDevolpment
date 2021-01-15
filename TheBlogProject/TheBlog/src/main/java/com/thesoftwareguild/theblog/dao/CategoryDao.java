/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Category;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface CategoryDao {
    // INTERFACE for Categorey
    public Category createCategory(Category category);
    
    public Category getCategoryById(int categoryId);
    
    public List<Category> getAllCategories();
    
    public void updateCategory(Category category);
    
    public void deleteCategory(int categoryId);
}
