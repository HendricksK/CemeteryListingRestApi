/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.CemeteryTest.ctx;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Kurvin Hendricks
 */
public class UserRoleTest {
    
    public UserRoleTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    private static Long id;
    public static ApplicationContext ctx;
    public static UserRoleRepository repo;
    
     @Test
     public void create() {
          System.out.println("User Role Test");
         repo = ctx.getBean(UserRoleRepository.class);
         
         UserRole newRole = new UserRole.Builder()
                 .setLevel(1)
                 .build();
         
         repo.save(newRole);
         id = newRole.getUserRoleID();
     }
     
     @Test(dependsOnMethods="create")
     public void read(){
         repo = ctx.getBean(UserRoleRepository.class);
         Assert.assertEquals(repo.findOne(id).getLevel(), 1);
         
     }
     
     @Test(dependsOnMethods="read")
     public void update(){
         repo = ctx.getBean(UserRoleRepository.class);
         
         UserRole oldRole = repo.findOne(id);
         UserRole newRole = new UserRole.Builder()
                 .UserRole(oldRole)
                 .setLevel(2)
                 .build();
             
         repo.save(newRole);         
         id = newRole.getUserRoleID();
         
         
         
     }
     
     @Test(dependsOnMethods="update")
     public void delete(){
         repo = ctx.getBean(UserRoleRepository.class);         
         repo.delete(repo.findOne(id));         
     }
     

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(ConnectionConfigTest.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
