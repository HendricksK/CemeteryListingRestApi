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
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.LoginService;
import com.cemeterylistingsweb.services.ViewListingBySubscriberService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.LoginServiceTest.ctx;
import static com.cemeterylistingswebtest.test.services.LoginServiceTest.repo;
import static com.cemeterylistingswebtest.test.services.ViewListingByDateOfBirthServiceTest.ctx;
import static com.cemeterylistingswebtest.test.services.ViewListingByDateOfBirthServiceTest.repoList;
import static com.cemeterylistingswebtest.test.services.ViewListingByDateOfBirthServiceTest.subRepo;
import static com.cemeterylistingswebtest.test.services.ViewListingByDateOfBirthServiceTest.userRepo;
import java.util.Calendar;
import java.util.List;
import junit.framework.Assert;
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
 * @author Ryno
 */
public class ViewListingsBySubscriberServiceTest {
    
    public ViewListingsBySubscriberServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     private static Long id, subID, userRoleID;
    public static ApplicationContext ctx;
    public static PublishedDeceasedListingRepository repoList;
    public static SubscriberRepository subRepo;
    public static UserRoleRepository userRepo;
    public static ViewListingBySubscriberService subserv;
     
    @Test(enabled = false)
     public void hello() {
        subserv = ctx.getBean(ViewListingBySubscriberService.class);
        repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
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
         //userRepo.save(userRole);
         //userRoleID = userRole.getUserRoleID();
         
         //Initialise subscriber
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
                 .setFirstName("Julie")
                 .setSurname("Romanov")
                 .setMaidenName("black")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1974")
                 .setDateOfDeath("14/02/2009")
                 .setGraveInscription("triple agent")
                 .setGraveNumber("2986")
                 .setImageOfBurialSite("/images/001.jpg")
                 
                 .setSubscriberSubmitID(subID)
                 .build();
         
         repoList.save(newListing);
         
         List<PublishedDeceasedListing> pubListDod = subserv.findListingBySubscriber(javaSqlDate, validDate);
         Assert.assertFalse(pubListDod.isEmpty());
         //Assert.assertTrue(pubListDod.isEmpty());
         repoList.delete(newListing);
         subRepo.delete(newSub);
        
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
