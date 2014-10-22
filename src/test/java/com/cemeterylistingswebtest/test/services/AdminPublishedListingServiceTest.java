/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.AdminUpdatePublishedListingService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.adminRegisterListingServiceTest.ctx;
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
 * @author Ryno
 */
public class AdminPublishedListingServiceTest {
    
    public AdminPublishedListingServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    Long id, subID, listingId;
    PublishedDeceasedListingRepository pubRepo;
    public static ApplicationContext ctx;
    public AdminUpdatePublishedListingService updatePubserv;
    public SubscriberRepository subRepo;
    public UserRoleRepository userRepo;
    
     @Test
     public void hello() {
     pubRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
     updatePubserv = ctx.getBean(AdminUpdatePublishedListingService.class);
     subRepo = ctx.getBean(SubscriberRepository.class);
     userRepo = ctx.getBean(UserRoleRepository.class);
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2008);
         calendar.set(Calendar.MONTH, Calendar.MARCH);
         calendar.set(Calendar.DATE, 5);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise date
         Calendar calendar2 = Calendar.getInstance();
         calendar2.set(Calendar.YEAR, 2014);
         calendar2.set(Calendar.MONTH, Calendar.JUNE);
         calendar2.set(Calendar.DATE, 20);
          
         java.sql.Date validDate = new java.sql.Date(calendar2.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(2)
                 .build();
  
  
            Subscriber newSub = new Subscriber.Builder()
                .setEmail("jackieChan@yahoo.com")
                .setFirstName("jackie")
                .setSurname("Chan")
                .setPwd("whaa")
                .setUsername("jChan")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
                 .setValidUntil(validDate)
                .build();
         subRepo.save(newSub);
         subID = newSub.getSubscriberID();
         
         PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("Dean")
                 .setSurname("Winchester")
                 
                 .setGender("Male")
                 .setDateOfBirth("27/07/1985")
                 .setDateOfDeath("14/10/2014")
                 .setGraveInscription("will probably come back")
                 .setGraveNumber("2474")
                 .setImageOfBurialSite("/images/004.jpg")
                 .setLastKnownContactName("sam")
                 .setLastKnownContactNumber("07255718927")

                 .setSubscriberSubmitID(subID)
                 .build();
         
         pubRepo.save(newListing);
         listingId=newListing.getPublishedListingID();
         
         PublishedDeceasedListing updateListing = new PublishedDeceasedListing.Builder()    
                 .PublishedDeceasedListing(newListing)
                 .setFirstName("Sam")
                 .setSurname(newListing.getSurname())
                 .setMaidenName(newListing.getMaidenName())
                 .setGender(newListing.getGender())
                 .setDateOfBirth(newListing.getDateOfBirth())
                 .setDateOfDeath(newListing.getDateOfDeath())
                 .setGraveInscription(newListing.getGraveInscription())
                 .setGraveNumber(newListing.getGraveNumber())
                 .setLastKnownContactName(newListing.getLastKnownContactName())
                 .setLastKnownContactNumber(newListing.getLastKnownContactNumber())
                 .setCemeteryID(newListing.getCemeteryID())                 
                 .setSubscriberApprovedID(newListing.getSubscriberApprovedID())
                 .setSubscriberSubmitID(newListing.getSubscriberSubmitID())
                 .setNames(newListing.getNames())

                 .setSubscriberSubmitID(subID)
                 .build();
         //listingId, "Sam", "Winchester", "", "Male", "20/07/1980", "14/10/2014", "Came back", "2474", "/images/004.jpg", "Dean", "07255718927", subID, null
         id = updatePubserv.updatePublishedlistingReturn(updateListing);
         
         PublishedDeceasedListing pubUpdate = updatePubserv.find(id);
         Assert.assertNotNull(updatePubserv.find(id));
         Assert.assertEquals(pubUpdate.getFirstName(), "Sam");
         
         PublishedDeceasedListing pub = pubRepo.findOne(listingId);
         Assert.assertNotNull(pubRepo.findOne(listingId));
         Assert.assertEquals(pub.getFirstName(), "Sam");
         pubRepo.delete(newListing);
         subRepo.delete(newSub);
         updatePubserv.remove(updateListing);
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
