/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Post;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface PostDao {
    // Interface for Post 
    public Post createPost(Post post);
    
    public Post getPostById(int postId);
    
    public List<Post> getAllPost();
    
    public List<Post> getAllPostByTagId(int tagId);
    
    public List<Post> getAllPostByCategoryId(int categoryId);
    
    public void updatePost(Post post);
    
    public void deletePost(int postId);
}
