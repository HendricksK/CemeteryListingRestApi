/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

//import com.cemeterylistingsweb.repository.UserRoleRepository;

import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.AdminRegisterUserRoleService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author Zaakir
 */
public class adminRegisterUserRoleServiceTest {
    
    private UserRoleRepository repo;
    public static ApplicationContext ctx;
    
    AdminRegisterUserRoleService adserv;
    
 @Test
  public void Test() {
      repo = ctx.getBean(UserRoleRepository.class);
      adserv = ctx.getBean(AdminRegisterUserRoleService.class);
      
      adserv.RegisterUserRole(66);
      Assert.assertFalse(repo.findAll().isEmpty());
      
      
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
