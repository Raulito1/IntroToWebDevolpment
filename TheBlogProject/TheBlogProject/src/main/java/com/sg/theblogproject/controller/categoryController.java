/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.controller;

import com.sg.theblogproject.model.Category;
import com.sg.theblogproject.model.Item;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sg.theblogproject.dao.ItemAndCategoryDao;

/**
 *
 * @author raulalvarado
 */
@Controller
public class categoryController {
    ItemAndCategoryDao dao;
    String error;

    @Inject
    public categoryController(ItemAndCategoryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/Category", method = RequestMethod.GET)
    public String displayCategoryPage(Model model) {
        // Get all the items from the DAO
        List<Category> categoryList = dao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("error", error);
        // error gets printed, once.
        error = null;
        return "Category/category";
    }

    @RequestMapping(value = "/displayCategoryDetail", method = RequestMethod.GET)
    public String displayCategoryDetail(HttpServletRequest request, Model model) {

        String categoryIdParameter = request.getParameter("categoryId");

        try {
            int categoryId = Integer.parseInt(categoryIdParameter);

            Category category = dao.getCategory(categoryId);

            model.addAttribute("category", category);
        } catch (NumberFormatException | DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Cannot find the category to display";
            model.addAttribute("error", error);
            // error gets printed, once.
            error = null;
        }

        return "Category/categoryDetail";
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        List<Category> categoryList = dao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        try {
            int categoryId = Integer.parseInt(categoryIdParameter);
            dao.deleteCategory(categoryId);
            error = null;
        } catch (NumberFormatException | DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Category does not exist to be deleted";
            model.addAttribute("error", error);
        }
        return "redirect:Category";
    }

    @RequestMapping(value = "/displayCategoryEditForm", method = RequestMethod.GET)
    public String displayCategoryEditForm(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        try {
            int categoryId = Integer.parseInt(categoryIdParameter);
            Category category = dao.getCategory(categoryId);
            model.addAttribute("category", category);
        } catch (NumberFormatException | DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Category does not exist to edit.";
            model.addAttribute("error", error);
            // error gets printed, once.
            error = null;
        }

        return "Category/editCategoryForm";
    }


    @RequestMapping(value = "/editCategory", method = RequestMethod.POST)
    public String editCategory(HttpServletRequest request, Model model) {
        Category updatedCategory = new Category();

        String categoryIdParameter = request.getParameter("categoryId");
        try {
            int categoryId = Integer.parseInt(categoryIdParameter);

            updatedCategory.setCategoryId(categoryId);

            Category oldCategory = dao.getCategory(categoryId);

            String updatedCategoryName = request.getParameter("categoryName");

            if (updatedCategoryName.equalsIgnoreCase("")) {
                updatedCategory.setCategoryName(oldCategory.getCategoryName());
            } else {
                updatedCategory.setCategoryName(updatedCategoryName);
            }

            dao.updateCategory(updatedCategory);
        } catch (NumberFormatException | DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Category to edit does not exist";
            model.addAttribute("error", error);
        }

        return "redirect:Category";
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(HttpServletRequest request) {

        Category category = new Category();

        category.setCategoryName(request.getParameter("categoryName"));

        dao.addCategory(category);

        return "redirect:Category";
    }
    @RequestMapping(value = "/catDetail", method = RequestMethod.GET)
    public String getCatDetials(Model model) {
        List<Item> catItems = dao.getAllItems();
        model.addAttribute("catItems", catItems);
        return "index";

    }
}
