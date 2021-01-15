/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.model.Post;
import com.thesoftwareguild.theblog.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raulalvarado
 */
public class TagDaoDbImpl implements TagDao {
    
    // inject dependency Injection for JDBCtemplate
    @Inject
    JdbcTemplate jdbcTemplate;
    
    //Create
    private static final String SQL_INSERT_TAG
            = "insert into Tag(tag_name) values(?)";
    // Read
    private static final String SQL_SELECT_TAG_BY_ID
            = "select * from Tag where tag_id = ?";
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";
    private static final String SQL_SELECT_TAG_BY_POST_ID
            = "select * from Tag t"
            + "join post_tag pt "
            + "on t.tag_id = pt.tag_id "
            + "where post.post_id = ?";
    private static final String SQL_SELECT_ALL_TAGS_BY_POST_ID
            = "select t.* from Tag t "
            + "join post_tag pt on t.tag_id = pt.tag_id "
            + "join post p on pt.post_id = p.post_id "
            + "where p.post_id = ?";
    // Update
    private static final String SQL_UPDATE_TAG
            = "update Tag set tag_name = ? where tag_id = ?";
    // Delete
    private static final String SQL_DELETE_TAG
            = "delete from Tag where tag_id = ?";
    // delete from join table with tag_id
    private static final String SQL_DELETE_POST_TAG
            = "delete from post_tag where tag_id = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag createTag(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_TAG,
                tag.getName());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tag.setTagId(newId);
        
        return tag;
    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG_BY_ID, new TagMapper(), tagId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS, new TagMapper());
    }

    @Override
    public List<Tag> getAllTagsByPostId(int postId) {
        List<Tag> tag = jdbcTemplate.query(SQL_SELECT_ALL_TAGS_BY_POST_ID, new TagMapper(), postId);
        return tag;
    }

    @Override
    public void updateTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(), tag.getTagId());
    }

    @Override
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_DELETE_POST_TAG, tagId);
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
    }
    
    //================== HELPER METHODS ===========
    private List<Tag> findTagsForPost(Post post) {
        return jdbcTemplate.query(SQL_SELECT_TAG_BY_POST_ID, new TagMapper(), post.getTags());
    }
    
    //================== TagMapper =================
    public static final class TagMapper implements RowMapper<Tag> {
        @Override 
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setTagId(rs.getInt("tag_id"));
            tag.setName(rs.getString("tag_name"));
            return tag;
        }
    }
}
