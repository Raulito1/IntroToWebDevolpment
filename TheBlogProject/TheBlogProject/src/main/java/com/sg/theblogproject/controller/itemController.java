/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.controller;


import com.sg.theblogproject.model.Category;
import com.sg.theblogproject.model.Item;
import java.util.ArrayList;
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
public class itemController {
        //setting the folder where the images will be saved
    //public static final String pictureFolder = "swImages/";

    ItemAndCategoryDao dao;
    String error;

    @Inject
    public itemController(ItemAndCategoryDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/Item", method = RequestMethod.GET)
    public String item(Model model) {
        List<Item> itemList = dao.getAllItems();
        model.addAttribute("itemList", itemList);
        List<Category> categoryList = dao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("error", error);
        error = null;

        return "Item/item";
    }

    //unused endpoint
    @RequestMapping(value = "/displayGetAllItems", method = RequestMethod.GET)
    public String displayItemPage(Model model) {
        // Get all the items from the DAO
        List<Item> itemList = dao.getAllItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("error", error);
        error = null;
        return "Item/item";
    }

    @RequestMapping(value = "/displayItemDetail", method = RequestMethod.GET)
    public String displayItemDetail(HttpServletRequest request, Model model) {

        String itemIdParameter = request.getParameter("itemId");

        try {
            int itemId = Integer.parseInt(itemIdParameter);

            Item item = dao.getItem(itemId);

            List<Category> categories = dao.getCategoriesByItemId(itemId);

            model.addAttribute("item", item);
            model.addAttribute("categories", categories);
        } catch (NumberFormatException ex) {
            error = "Trouble finding the item to display";
            model.addAttribute("error", error);
            error = null;
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Error displaying the item details";
            model.addAttribute("error", error);
            error = null;
        }

        return "Item/itemDetail";
    }


    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public String deleteItem(HttpServletRequest request) {
        String itemIdParameter = request.getParameter("itemId");
        try {
            int itemId = Integer.parseInt(itemIdParameter);
            dao.deleteItem(itemId);
        } catch (NumberFormatException ex) {
            error = "Trouble finding the item to delete";
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Error deleteing the item, delete will not be saved";
        }
        return "redirect:Item";
    }

    @RequestMapping(value = "/displayEditItemForm", method = RequestMethod.GET)
    public String displayEditItemForm(HttpServletRequest request, Model model) {
        String itemIdParameter = request.getParameter("itemId");
        try {
            int itemId = Integer.parseInt(itemIdParameter);
            Item item = dao.getItem(itemId);

            List<Category> categoryList = dao.getAllCategories();
            List<Category> categories = dao.getCategoriesByItemId(itemId);

            model.addAttribute("categories", categories);

            model.addAttribute("categoryList", categoryList);

            model.addAttribute("item", item);
        } catch (NumberFormatException ex) {
            error = "Trouble finding the item to edit";
            model.addAttribute("error", error);
            error = null;
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Error displaying the item details";
            model.addAttribute("error", error);
            error = null;
        }

        return "Item/editItemForm";
    }

    @RequestMapping(value = "/displayAddItemForm", method = RequestMethod.GET)
    public String displayAddItemForm(Model model) {

        List<Category> categoryList = dao.getAllCategories();

        model.addAttribute("categoryList", categoryList);

        return "Item/addItemForm";
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    public String editItem(HttpServletRequest request) {

        Item updatedItem = new Item();
        //ID's are set auto incremented, so negative numbers should be impossible.
        // this will throw an error when redirected and print the error message;
        int itemId = -1;

        try {
            String itemIdParameter = request.getParameter("itemId");
            itemId = Integer.parseInt(itemIdParameter);

            updatedItem.setItemId(itemId);

            Item oldItemInfo = dao.getItem(itemId);

            String updatedItemName = request.getParameter("itemName");

            if (updatedItemName.equalsIgnoreCase("")) {
                updatedItem.setItemName(oldItemInfo.getItemName());
            } else {
                updatedItem.setItemName(updatedItemName);
            }

            String updatedDescription = request.getParameter("description");

            if (updatedItemName.equalsIgnoreCase("")) {
                updatedItem.setDescription(oldItemInfo.getDescription());
            } else {
                updatedItem.setDescription(updatedDescription);
            }

           
            String updatedFeaturedItem = request.getParameter("featured");
            updatedItem.setFeaturedItem(Boolean.parseBoolean(updatedFeaturedItem));



            String[] categoryIdList = request.getParameterValues("categories");

            if (categoryIdList == null) {
                updatedItem.setCategories(oldItemInfo.getCategories());
            } else {
                List<Category> updatedCategoryList = new ArrayList<>();

                for (String currentCategoryId : categoryIdList) {
                    int categoryId = Integer.parseInt(currentCategoryId);
                    Category category = dao.getCategory(categoryId);
                    updatedCategoryList.add(category);
                }
                updatedItem.setCategories(updatedCategoryList);
            }
            
            updatedItem.setFilename(oldItemInfo.getFilename());
            updatedItem.setImageTitle(oldItemInfo.getImageTitle());
            

            dao.updateItem(updatedItem);
        } catch (NumberFormatException ex) {
            error = "Trouble finding the item to edit";
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            error = "Error displaying the item details";
        }

        return "redirect:displayItemDetail?itemId=" + itemId;
    }

    //UPDATED /createItem endpoint to read in fileName and path
    @RequestMapping(value = "/createItem", method = RequestMethod.POST)
    public String createItem(HttpServletRequest request, 
            Model model) {
         

         Item item = new Item();
        
            item.setItemName(request.getParameter("nameInput"));
            item.setDescription(request.getParameter("descriptionInput"));
            item.setFeaturedItem(Boolean.parseBoolean(request.getParameter("featured")));

            String[] listOfCategories = request.getParameterValues("categoryInput");

            if (listOfCategories == null) {
                //do nothing
            } else {
                //Creating a new category to hold the categories selected
                List<Category> categoryList = new ArrayList<>();

                for (String currentId : listOfCategories) {
                    int categoryId = Integer.parseInt(currentId);
                    Category category = dao.getCategory(categoryId);

                    categoryList.add(category);
                }

                item.setCategories(categoryList);
            }

            
            dao.addItem(item);
            
          
        return "redirect:Item";
    }


    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getfeaturedItems(Model model) {
        List<Item> featuredItems = dao.getfeaturedItems();
        model.addAttribute("featuredItems", featuredItems);
        return "index";

    }

    @RequestMapping(value = "/featured", method = RequestMethod.GET)
    public String getmyfeaturedItems(Model model) {
        List<Item> featuredItems = dao.getfeaturedItems();
        model.addAttribute("featuredItems", featuredItems);
        return "index";

    }

    @RequestMapping(value = "/displaySearchItems", method = RequestMethod.GET)
    public String displaySearch(Model model) {
        // Get all the items from the DAO
        List<Item> itemList = dao.getAllItems();
        model.addAttribute("itemList", itemList);
        return "Item/item";
    }

}
