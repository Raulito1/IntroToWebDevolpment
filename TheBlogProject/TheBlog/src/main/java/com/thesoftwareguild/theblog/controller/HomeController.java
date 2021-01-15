/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.controller;

import com.thesoftwareguild.theblog.dao.CategoryDao;
import com.thesoftwareguild.theblog.dao.PostDao;
import com.thesoftwareguild.theblog.dao.TagDao;
import com.thesoftwareguild.theblog.model.Category;
import com.thesoftwareguild.theblog.model.Post;
import com.thesoftwareguild.theblog.model.Tag;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class HomeController {
    // dependency Injection
     @Inject
     PostDao postdao;
     
     @Inject
     CategoryDao categorydao;
     
     @Inject
     TagDao tagdao;
     
     @RequestMapping(value ={"/", "/home"},method = RequestMethod.GET)
     public String displayHomePage(HttpServletRequest request, Model model) {
         List<Post> postList = postdao.getAllPost();
         model.addAttribute("postList", postList);
         List<Category> categoryList = categorydao.getAllCategories();
         model.addAttribute("categoryList", categoryList);
         return "home";
     }
     
     @GetMapping("/displayCategoyPost")
     public String displayCategoryPost(HttpServletRequest request, Model model) {
         String categoryIdParameter = request.getParameter("categoryId");
         int categoryId = Integer.parseInt(categoryIdParameter);
         
         Category category = categorydao.getCategoryById(categoryId);
         model.addAttribute("category", category);
         
         List<Post> postList = postdao.getAllPostByCategoryId(categoryId);
         model.addAttribute("postList", postList);
         
         return "categoryPost";
     }
     
     @GetMapping("/displayTagPost")
     public String displayTagPost(HttpServletRequest request, Model model) {
         String tagIdParameter = request.getParameter("tagId");
         int tagId = Integer.parseInt(tagIdParameter);
         
         Tag tag = tagdao.getTagById(tagId);
         model.addAttribute("tag", tag);
         
         List<Post> postList = postdao .getAllPostByTagId(tagId);
         model.addAttribute("postList", postList);
         
         return "tagPost";
     }
}
