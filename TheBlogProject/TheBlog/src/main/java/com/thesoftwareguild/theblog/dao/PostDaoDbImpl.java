/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.theblog.dao;

import com.thesoftwareguild.theblog.dao.CategoryDaoDbImpl.CategoryMapper;
import com.thesoftwareguild.theblog.dao.RoleDaoDbImpl.RoleMapper;
import com.thesoftwareguild.theblog.dao.TagDaoDbImpl.TagMapper;
import com.thesoftwareguild.theblog.dao.UserDaoDbImpl.UserMapper;
import com.thesoftwareguild.theblog.model.Category;
import com.thesoftwareguild.theblog.model.Post;
import com.thesoftwareguild.theblog.model.Role;
import com.thesoftwareguild.theblog.model.Tag;
import com.thesoftwareguild.theblog.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class PostDaoDbImpl implements PostDao {
    // inject jdbcTemplate injection
    @Inject
    JdbcTemplate jdbcTemplate;
    
    //======== SQL STATEMENTS ============
    // CREATE ==============
    private static final String SQL_INSERT_POST
            = "insert into post(title, post_date, content, user_id, state, reference_id, category_id) "
            + "values(?,?,?,?,?,?,?)";
    // Create == insert values into join table post_tag
    
    private static final String SQL_INSERT_POST_TAG
            = "insert into post_tag(post_id, tag_id) values(?,?)";
    // READ ================
    private static final String SQL_SELECT_POST_BY_ID
            = "select * from post where post_id = ?";
    private static final String SQL_SELECT_ALL_POST
            = "select * from post " + "order by post_date desc";
     private static final String SQL_SELECT_POST_BY_TAG_ID
            = "select p.* from podt p "
            + "join post_tag pt "
            + "on p.post_id = pt.post_id "
            + "where pt.tag_id = ?";
     private static final String SQL_SELECT_POST_BY_CATEGORY_ID
             = "select * from post where category_id = ?";
     private static final String SQL_SELECT_CATEGORY_BY_POST_ID
             = "select c.* from post p "
             + "join category c "
             + "on c.category_id = p.category_id "
             + "where p.post_id = ?";
     private static final String SQL_SELECT_REFERENCED_POST_BY_POST_ID
             = "select p2.* "
             + "from post p "
             + "join post p2 "
             + "on p2.post_id = p.reference_id "
             + "where p.post_id = ?";
     private static final String SQL_SELECT_TAGS_BY_POST_ID
             = "select t.* "
             + "from tag t "
             + "join post_tag pt "
             + "on t.tag_id = pt.tag_id "
             + "where pt.post_id = ?";
     private static final String SQL_SELECT_USER_BY_POST_ID
             = "select u.* "
             + "from user u "
             + "join post p "
             + "on u.user_id = p.user_id "
             + "where p.post_id = ?";
     private static final String SQL_SELECT_ROLES_FOR_USER_BY_USER_ID
             = "select r.* "
             + "from role r "
             + "join user_role ur "
             + "on r.role_id = ur.role_id "
             + "where ur.user_id = ?";
    // UPDATE ==============
    private static final String SQL_UPDATE_POST
            = "update post set "
            + "title = ?, post_date = ?, content = ?, user_id = ?, state = ?, "
            + "referenced_id = ?, category_id = ? "
            + "where post_id = ?";
    // DELETE ================
    private static final String SQL_DELETE_POST_TAG
            = "delete from post_tag where post_id =?";
    private static final String SQL_DELETE_POST
            = "delete from Post where post_id = ?";
    private static final String SQL_DELETE_REFERENCED_POST
            = "delete from post where referenced_id = ?";
    private static final String SQL_DELETE_REPLY_POST_TAG
            ="delete pt from post_tag pt "
            + "join post p "
            + "on pt.post_id = p.post_id "
            + "where referenced_id = ?";
   
    
    // ========= Interface Method Implementations 
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post createPost(Post post) {
        jdbcTemplate.update(SQL_INSERT_POST,
                    post.getTitle(),
                    post.getPostDate(),
                    post.getContent(),
                    post.getUser().getUserId(),
                    post.getState(),
                    (post.getReferencedPost() == null)? null : post.getReferencedPost().getPostId(),
                    (post.getCategory() == null) ? null : post.getCategory().getCategoryId());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        post.setPostId(newId);
        
        addPostTag(post);
        return post;
    }

    @Override
    public Post getPostById(int postId) {
        try {
            Post post  = jdbcTemplate.queryForObject(SQL_SELECT_POST_BY_ID, new PostMapper(), postId);
            post.setCategory(getCategoryByPostId(postId));
            User user = getUserByPostId(postId);
            user.setRoles(getRolesForUserByUserId(user.getUserId()));
            post.setUser(user);
            post.setTags(getTagsByPostId(post.getPostId()));
            Post rp = getReferencedPostByPostId(post.getPostId());
            if (rp != null) {
                rp.setCategory(getCategoryByPostId(rp.getPostId()));
                User u = getUserByPostId(rp.getPostId());
                u.setRoles(getRolesForUserByUserId(u.getUserId()));
                rp.setUser(u);
                rp.setTags(getTagsByPostId(rp.getPostId()));
            }
            post.setReferencedPost(rp);
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = jdbcTemplate.query(SQL_SELECT_ALL_POST, new PostMapper());
        populatePost(posts);
        return posts;
    }

    @Override
    public List<Post> getAllPostByTagId(int tagId) {
        List<Post> post = jdbcTemplate.query(SQL_SELECT_POST_BY_TAG_ID, new PostMapper(), tagId);
        populatePost(post);
        return post;
    }

    @Override
    public List<Post> getAllPostByCategoryId(int categoryId) {
        List<Post> post = jdbcTemplate.query(SQL_SELECT_POST_BY_CATEGORY_ID, new PostMapper(), categoryId);
        populatePost(post);
        return post;
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                    post.getTitle(),
                    post.getPostDate(),
                    post.getContent(),
                    post.getUser().getUserId(),
                    post.getState(),
                    (post.getReferencedPost() == null)? null : post.getReferencedPost().getPostId(),
                    (post.getCategory() == null)? null : post.getCategory().getCategoryId(),post.getPostId());
    }

    @Override
    public void deletePost(int postId) {
        jdbcTemplate.update(SQL_DELETE_POST, postId);
        jdbcTemplate.update(SQL_DELETE_REPLY_POST_TAG, postId);
        jdbcTemplate.update(SQL_DELETE_REFERENCED_POST, postId);
        jdbcTemplate.update(SQL_DELETE_POST_TAG, postId);
        
    }
    //=============== HELPER METHODS =====================
    private void populatePost(List<Post> post) {
        for (Post p : post) {
            p.setCategory(getCategoryByPostId(p.getPostId()));
            p.setTags(getTagsByPostId(p.getPostId()));
            User user = getUserByPostId(p.getPostId());
            user.setRoles(getRolesForUserByUserId(user.getUserId()));
            p.setUser(user);
            Post rp = getReferencedPostByPostId(p.getPostId());
            if (rp != null) {
                rp.setCategory(getCategoryByPostId(rp.getPostId()));
                User u = getUserByPostId(rp.getPostId());
                u.setRoles(getRolesForUserByUserId(u.getUserId()));
                rp.setUser(u);
                rp.setTags(getTagsByPostId(rp.getPostId()));
            }
            p.setReferencedPost(rp);
        }
    }
    
    private void addPostTag(Post post) {
        post.getTags().forEach(tag -> {
            jdbcTemplate.update(SQL_INSERT_POST_TAG, post.getPostId(), tag.getTagId());
        });
    }
    
    private Category getCategoryByPostId(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BY_POST_ID, new CategoryMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private Post getReferencedPostByPostId(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_REFERENCED_POST_BY_POST_ID, new PostMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private List<Tag> getTagsByPostId(int postId) {
        return jdbcTemplate.query(SQL_SELECT_TAGS_BY_POST_ID, new TagMapper(), postId);
    }
    
    private User getUserByPostId(int postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_POST_ID, new UserMapper(), postId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    private Set<Role> getRolesForUserByUserId(int userId) {
        List<Role> role = jdbcTemplate.query(SQL_SELECT_ROLES_FOR_USER_BY_USER_ID, new RoleMapper(), userId);
        Set<Role> roleSet = new HashSet<>(role);
        return roleSet;
    }
    
    // ============== POSTMAPPER METHOD ======================
    public static final class PostMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setTitle(rs.getString("title"));
            post.setPostDate(rs.getDate("post_date").toLocalDate());
            post.setContent(rs.getString("content"));
            post.setState(rs.getInt("state"));
            return post;
        }
    }
}
