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
    private static Long id, subID, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static CemeteryRepository cemRepo;
    public static RequiresApprovalDeceasedListingRepository repoList;
    public static UserRoleRepository userRepo;
    public static PublishedDeceasedListingRepository pubRepo;
    public PublishListingService pubServ;
     
    @Test
     public void publishTest() {
         pubServ = ctx.getBean(PublishListingService.class);
         repo = ctx.getBean(SubscriberRepository.class);  
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2008);
         calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
         calendar.set(Calendar.DATE, 4);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(2)
                 .build();
         //userRepo.save(userRole);
         //userRoleID = userRole.getUserRoleID();
         
         //Initialise subscriber
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("manfredOsulivan@horseRaddish.com")
                .setFirstName("Manfred")
                .setSurname("Osulivan")
                .setPwd("jesus")
                .setUsername("ManiFredOssy")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
                .build();
         repo.save(newSub);
         subID = newSub.getSubscriberID();
         
         //Finally Initialise RequiresApprovalDeceasedListing
         RequiresApprovalDeceasedListing newListing = new RequiresApprovalDeceasedListing.Builder()
                 .setFirstName("Hendrika")
                 .setSurname("Fourie")
                 .setMaidenName("Gerber")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1969")
                 .setDateOfDeath("14/02/2005")
                 .setGraveInscription("Hippiest person eva")
                 .setGraveNumber("2456")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berry")
                 .setLastKnownContactNumber("0725576482")
                 .setSubscriberSubmitID(subID)
                 //cemetery id
                 
                 //names
                 
                 .build();
         
         repoList.save(newListing);
         id = newListing.getRequiresApprovalDeceasedListingID();   
         
         pubServ.publishListings(newListing);
        //boolean empty = pubRepo.findAll().isEmpty();
         Assert.assertFalse(repoList.findAll().isEmpty());
          //Assert.assertTrue(empty);
         //Assert.assertFalse(empty);
         
         
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
