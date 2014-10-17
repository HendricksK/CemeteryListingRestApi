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
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.PublishListingService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.Calendar;
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
 * @author Ryno
 */
public class PublishListingServiceTest {
    
    public PublishListingServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id,id2, subID, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static CemeteryRepository cemRepo;
    public static RequiresApprovalDeceasedListingRepository repoList;
    public static UserRoleRepository userRepo;
    public static PublishedDeceasedListingRepository pubRepo;
    public PublishListingService pubServ;
     
    @Test()
     public void publishTest() {
         pubServ = ctx.getBean(PublishListingService.class);
         repo = ctx.getBean(SubscriberRepository.class);  
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         pubRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
         
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
         //userRepo.save(userRole);
         //userRoleID = userRole.getUserRoleID();
         
         //Initialise subscriber
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("bwillis@gmail.com")
                .setFirstName("bruce")
                .setSurname("willis")
                .setPwd("diehard")
                .setUsername("bwillis")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
                .build();
         repo.save(newSub);
         subID = newSub.getSubscriberID();
         
         //Finally Initialise RequiresApprovalDeceasedListing
         RequiresApprovalDeceasedListing newListing = new RequiresApprovalDeceasedListing.Builder()
                 .setFirstName("maggie")
                 .setSurname("mcClain")
                 .setMaidenName("Gerber")
                 .setGender("Female")
                 .setDateOfBirth("10/06/1967")
                 .setDateOfDeath("14/03/2006")
                 .setGraveInscription("was a mother")
                 .setGraveNumber("2521")
                 .setImageOfBurialSite("/images/003.jpg")
                 .setLastKnownContactName("john")
                 .setLastKnownContactNumber("07273218482")
                 .setSubscriberSubmitID(subID)
                 //cemetery id
                 
                 //names
                 
                 .build();
         
         repoList.save(newListing);
         id = newListing.getRequiresApprovalDeceasedListingID();   
         
         id2 = pubServ.publishListingsReturn(newListing);
        boolean empty = pubRepo.findAll().isEmpty();
         //Assert.assertEquals(repoList.findAll().size(), 1);
          Assert.assertFalse(empty);
         Assert.assertFalse(repoList.findAll().isEmpty());
         repoList.delete(id);
         pubRepo.delete(id2);
         repo.delete(subID);
         
         
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
