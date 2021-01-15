/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.controller;

import com.thesoftwareguild.theblog.dao.CategoryDao;
import com.thesoftwareguild.theblog.model.Category;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class CategoryController {
   // dependency Injection  for DAO
    CategoryDao dao;
    
    @Inject 
    public CategoryController(CategoryDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayCategoryPage", method = RequestMethod.GET)
    public String displayCategoryPage(Model model) {
        List<Category> categoryList = dao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "category";
    }
    
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(HttpServletRequest request) {
        Category category = new Category();
        category.setName(request.getParameter("name"));
        dao.createCategory(category);
        return "redirect:/displayCategoryPage";
    }
    
    @RequestMapping(value = "/categoryDetails", method = RequestMethod.GET)
    public String displayCategoryDetails(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        Category category = dao.getCategoryById(categoryId);
        model.addAttribute("categoryId", categoryId);
        return "categoryDetails";
    }
    
    @RequestMapping(value = "deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request) {
        String categoryIdParamter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParamter);
        dao.deleteCategory(categoryId);
        return "redirect:displayCategoryPage";
    }
    
    @RequestMapping(value = "displayEditCategoryForm", method = RequestMethod.GET)
    public String displayEditCategoryForm(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        Category category = dao.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "editcategoryForm";
    }
    
    @RequestMapping(value = "editCategory" , method = RequestMethod.POST)
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editCategoryForm";
        }
        dao.updateCategory(category);
        return "redirect:displayCategoryPage";
    } 
}
