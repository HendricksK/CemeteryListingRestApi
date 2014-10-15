/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.ApproveSubscriberService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Zaakir
 */
public class approveSubscriberServiceTest {
     SubscriberRepository SubscrRepo;
    public static ApplicationContext ctx;
    
    ApproveSubscriberService adserv;
    
 @Test
  public void Test() {
      
      SubscrRepo = ctx.getBean(SubscriberRepository.class);
      adserv = ctx.getBean(ApproveSubscriberService.class);
      
      adserv.avalaibleUsername("test");
       Assert.assertTrue(SubscrRepo.findAll().isEmpty());
      adserv.avalaibleUsername("test");
      Assert.assertEquals(SubscrRepo.count(), 0);
      
      
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
