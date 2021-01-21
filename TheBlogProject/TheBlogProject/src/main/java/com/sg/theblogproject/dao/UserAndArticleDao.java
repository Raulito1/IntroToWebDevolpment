/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.dao;

import com.sg.theblogproject.model.Article;
import com.sg.theblogproject.model.User;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface UserAndArticleDao {
    public User addUser(User user);
    
    public void deleteUser(int id);
    
    public void updateUser(User user);
    
    public User getUser(int id);
    
    public List<User> getAllUsers();
    
    public User getUserByArticleId(int articleId); 
    
    public Article addArticle(Article article);
    
    public void deleteArticle(int id);
    
    public void updateArticle(Article article);
    
    public Article getArticle(int id);
    
    public List<Article> getAllArticles();
    
    public void deleteAllAuthorities();
}
