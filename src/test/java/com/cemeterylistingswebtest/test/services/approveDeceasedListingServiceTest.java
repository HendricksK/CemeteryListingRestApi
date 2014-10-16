/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.services.ApproveDeceasedListingService;
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
public class approveDeceasedListingServiceTest {
   // RequiresApprovalDeceasedListingRepository deadRepo;
    public static ApplicationContext ctx;
    
    ApproveDeceasedListingService adserv;
    
 @Test
  public void Test() {
      
      //deadRepo = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
      adserv = ctx.getBean(ApproveDeceasedListingService.class);
      
      //adserv.approveDeceasedListing(157l);
      
     
       Assert.assertFalse(adserv.approveDeceasedListing(157l));
      
      
      
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
