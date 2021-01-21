/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.theblogproject.dao;

import com.sg.theblogproject.model.Article;
import com.sg.theblogproject.model.Authorities;
import com.sg.theblogproject.model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class UserAndArticleDaoTest {
    
    UserAndArticleDao dao;
    
    public UserAndArticleDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        dao = (UserAndArticleDao)ctx.getBean("squatchWatchUserAndArticleDao");
        
        cleanUp();
    }
    
    @After
    public void tearDown() {
        cleanUp();
    }

    /**
     * Test of addUser method, of class UserAndArticleDao.
     */
    @Test
    public void testAddUser() {
         User newUser = addNewUser();
        List<User> userList = dao.getAllUsers();
        assertEquals(userList.size(), 1);
    }

    /**
     * Test of deleteUser method, of class UserAndArticleDao.
     */
    @Test
    public void testDeleteUser() {
         User newUser = addNewUser();
        List<User> userList = dao.getAllUsers();
        assertEquals(userList.size(), 1);
        
        dao.deleteUser(newUser.getUserId());
        assertNull(dao.getUser(newUser.getUserId()));
    }

    /**
     * Test of updateUser method, of class UserAndArticleDao.
     */
    @Test
    public void testUpdateUser() {
         User newUser = addNewUser();
        newUser.setUserName("JimmyJay");
        dao.updateUser(newUser);
        User fromDb = dao.getUser(newUser.getUserId());
        assertEquals(newUser, fromDb);
    }

    /**
     * Test of getUser method, of class UserAndArticleDao.
     */
    @Test
    public void testGetUser() {
        User user = addNewUser();
        User user3 = dao.getUser(user.getUserId());
        assertEquals(user, user3);
    }

    /**
     * Test of getAllUsers method, of class UserAndArticleDao.
     */
    @Test
    public void testGetAllUsers() {
         User user1 = addNewUser();

        User user2 = addNewUser();

        
        List<User> userList = dao.getAllUsers();
        assertEquals(userList.size(), 2);
    }

    /**
     * Test of getUserByArticleId method, of class UserAndArticleDao.
     */
    @Test
    public void testGetUserByArticleId() {
    }

    /**
     * Test of addArticle method, of class UserAndArticleDao.
     */
    @Test
    public void testAddArticle() {
               Article newArt = addNewArticle();
        dao.addArticle(newArt);
        List<Article> artList = dao.getAllArticles();
        assertEquals(artList.size(), 1);
    }

    /**
     * Test of deleteArticle method, of class UserAndArticleDao.
     */
    @Test
    public void testDeleteArticle() {
        Article newArt = addNewArticle();
        dao.addArticle(newArt);
        List<Article> artList = dao.getAllArticles();
        assertEquals(artList.size(), 1);
        
        dao.deleteArticle(newArt.getArticleId());
        assertNull(dao.getArticle(newArt.getArticleId()));
    }

    /**
     * Test of updateArticle method, of class UserAndArticleDao.
     */
    @Test
    public void testUpdateArticle() {
    }

    /**
     * Test of getArticle method, of class UserAndArticleDao.
     */
    @Test
    public void testGetArticle() {
    }

    /**
     * Test of getAllArticles method, of class UserAndArticleDao.
     */
    @Test
    public void testGetAllArticles() {
    }

    /**
     * Test of deleteAllAuthorities method, of class UserAndArticleDao.
     */
    @Test
    public void testDeleteAllAuthorities() {
    }
    
    public void cleanUp() {
        
        dao.deleteAllAuthorities();
        
        List<User> user = dao.getAllUsers();
        user.forEach((currentUser) -> {
            dao.deleteUser(currentUser.getUserId());
        });
        
        List<Article> article = dao.getAllArticles();
        article.forEach((currentArticle) -> {
            dao.deleteArticle(currentArticle.getArticleId());
        });
    }
    
    public User addNewUser() {
        Authorities auth = new Authorities();
        auth.setAuthority("admin");
        
        User user1 = new User();
        user1.setUserName("TotallyNotABigfoot");
        user1.setPassword("password");
        user1.setEnabled(1);
        List<Authorities> authList = new ArrayList<>();
        authList.add(auth);
        user1.setPermission(authList);
//        user1.setUserId(5);
        
        dao.addUser(user1); 
        
        return user1;
    }
    
    public Article addNewArticle() {
        User user = addNewUser();
        Article art = new Article();
        art.setUser(user);
        art.setArticleDate(LocalDate.now());
        art.setContent("Generic Article Content Blah Blah Blah Blurry Bigfoot Pictures");
        art.setUserId(user.getUserId());
        return art;
    }

}