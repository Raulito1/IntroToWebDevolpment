/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Tag;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface TagDao {
    // Interface for Tag
    public Tag createTag(Tag tag);
    
    public Tag getTagById(int tagId);
    
    public List<Tag> getAllTags();
    
    public List<Tag> getAllTagsByPostId(int postId);
    
    public void updateTag(Tag tag);
    
    public void deleteTag(int tagId);
}
