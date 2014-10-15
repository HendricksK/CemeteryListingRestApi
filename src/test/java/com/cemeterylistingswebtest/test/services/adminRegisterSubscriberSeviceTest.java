/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.AdminRegisterSubscriberService;
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
public class adminRegisterSubscriberSeviceTest {
    
    public static ApplicationContext ctx;
    SubscriberRepository SubscrRepo;
    public AdminRegisterSubscriberService adserv;
    
 @Test
  public void Test() {
      
      SubscrRepo = ctx.getBean(SubscriberRepository.class);
      adserv = ctx.getBean(AdminRegisterSubscriberService.class);
      
      adserv.registerSubscriber("chuck@hotmal.com", "first", "last", "chuck27", "encrypted", null, 1);
      Assert.assertFalse(SubscrRepo.findAll().isEmpty());

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
