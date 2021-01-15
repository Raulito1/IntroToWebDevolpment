/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.controller;

import com.thesoftwareguild.theblog.dao.CategoryDao;
import com.thesoftwareguild.theblog.dao.PostDao;
import com.thesoftwareguild.theblog.dao.TagDao;
import com.thesoftwareguild.theblog.dao.UserDao;
import com.thesoftwareguild.theblog.model.Category;
import com.thesoftwareguild.theblog.model.Post;
import com.thesoftwareguild.theblog.model.Tag;
import com.thesoftwareguild.theblog.model.User;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class PostController {
    private PostDao postDao;
    
    @Inject
    public void PostController(PostDao postDao) {
        this.postDao = postDao;
    }
    
    @Inject
    UserDao userDao;
    
    @Inject
    CategoryDao categoryDao;
    
    @Inject
    TagDao tagDao;
    
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String displayPostPage(Model model) {
        List<Post> postList = postDao.getAllPost();
        model.addAttribute("postList", postList);
        List<Category> categoryList = categoryDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "post";
    }
    
    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public String createPost(HttpServletRequest request,Principal p) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        Post post = new Post();
        post.setPostDate(LocalDate.now());
        post.setContent(request.getParameter("postContent"));
        if (request.getParameter("postCategory") != null && !request.getParameter("postCategory").isEmpty()) {
            post.setCategory(categoryDao.getCategoryById(Integer.parseInt(request.getParameter("postCategory"))));
        }
        if (request.getParameter("referencedPost") != null) {
            post.setReferencedPost(postDao.getPostById(Integer.parseInt(request.getParameter("referencedPost"))));
        }
        post.setState(2);
        //post.setUser(userDao.getUserByUsername(p.getName()));
        post.setTitle(request.getParameter("postTitle"));
        if (request.getParameterValues("tags") != null) {
            String[] tagsList = request.getParameterValues("tags");
            List<Tag> postTags = new ArrayList<>();
            List<Tag> allTags = tagDao.getAllTags();
            boolean isNew = true;
            for(String tagString : tagsList) {
                for (Tag t : allTags) {
                    if (tagString.equalsIgnoreCase(t.getName())) {
                        isNew = false;
                        postTags.add(t);
                    }
                }
                if (isNew == true) {
                    Tag t = new Tag();
                    t.setName(tagString);
                    t = tagDao.createTag(t);
                    postTags.add(t);
                }
            }
            post.setTags(postTags);
        }
        postDao.createPost(post);
        return "redirect:approvePost";
    }
    
    @GetMapping(value = "/approvePost")
    public String displayApprovalPost(Model model) {
        List<Post> postList = postDao.getAllPost();
        model.addAttribute("postList", postList);
        return "approvePost";
    }
    
    @RequestMapping(value = "/approveThisPost", method = RequestMethod.POST)
    public String updatePostApproval(int postId) {
        Post post = postDao.getPostById(postId);
        post.setState(1);
        postDao.updatePost(post);
        return "redirect:home";
    }
    
    @RequestMapping(value = "/postDetails", method = RequestMethod.GET)
    public String displayPostDetails(HttpServletRequest request, Model model) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        Post post = postDao.getPostById(postId);
        model.addAttribute("postId", postId);
        return "postDetails";
    }
    
    @RequestMapping(value = "editPost", method = RequestMethod.GET)
    public String displayEditPostForm(HttpServletRequest request, Model model) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        Post post = postDao.getPostById(postId);
        List<Category> categoryList = categoryDao.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("post", post);
        return "editPost";
    }
    
    @RequestMapping(value = "deletePost", method = RequestMethod.GET)
    public String deletePost(HttpServletRequest request) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        postDao.deletePost(postId);
        return "redirect:home";
    }
    
    @RequestMapping(value = "updatePost", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute("post") Post post, BindingResult result, HttpServletRequest request) {
        String userIdParameter = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParameter);
        
        User user = userDao.getUserById(userId);
        post.setUser(user);
        
        if (request.getParameterValues("tags")!= null) {
            String[] tagsList = request.getParameterValues("tags");
            List<Tag> postTags = new ArrayList<>();
            List<Tag> allTags = tagDao.getAllTags();
            boolean isNew = true;
            for (String tagStr : tagsList) {
                for (Tag t : allTags) {
                    if (tagStr.equalsIgnoreCase(t.getName())) {
                        isNew = false;
                        postTags.add(t);
                    }
                }
                if (isNew == true) {
                    Tag t = new Tag();
                    t.setName(tagStr);
                    t = tagDao.createTag(t);
                    postTags.add(t);
                }
            }
            post.setTags(postTags);
        }
        postDao.updatePost(post);
        return "redirect:approvePost";
    }
}
