/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.ApproveDeceasedListingService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.Calendar;
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
    Long id,subID;
    ApproveDeceasedListingService adserv;
    public static SubscriberRepository repo;
    public static CemeteryRepository cemRepo;
    public static RequiresApprovalDeceasedListingRepository repoList;
    public static UserRoleRepository userRepo;
    
 @Test
  public void Test() {
      
      //deadRepo = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
      adserv = ctx.getBean(ApproveDeceasedListingService.class);
         repo = ctx.getBean(SubscriberRepository.class);  
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2010);
         calendar.set(Calendar.MONTH, Calendar.JUNE);
         calendar.set(Calendar.DATE, 14);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(2)
                 .build();
        //Initialise subscriber
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("greaper@gmail.com")
                .setFirstName("Grim")
                .setSurname("reaper")
                .setPwd("souls")
                .setUsername("greaper")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
                .build();
         repo.save(newSub);
         subID = newSub.getSubscriberID();
         
         //Finally Initialise RequiresApprovalDeceasedListing
         RequiresApprovalDeceasedListing newListing = new RequiresApprovalDeceasedListing.Builder()
                 .setFirstName("elizabeth")
                 .setSurname("willow")
                 .setMaidenName("charles")
                 .setGender("Female")
                 .setDateOfBirth("16/06/1960")
                 .setDateOfDeath("14/011/2014")
                 .setGraveInscription("lived long and prospered")
                 .setGraveNumber("2811")
                 .setImageOfBurialSite("/images/003.jpg")
                 
                 .setSubscriberSubmitID(subID)
                 //cemetery id
                 
                 //names
                 
                 .build();
         
         repoList.save(newListing);
         id = newListing.getRequiresApprovalDeceasedListingID();   
         boolean approve = adserv.approveDeceasedListing(id);
         Assert.assertFalse(approve);
      
         
      
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
