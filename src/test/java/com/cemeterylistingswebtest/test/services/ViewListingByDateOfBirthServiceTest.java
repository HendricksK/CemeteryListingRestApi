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
import com.cemeterylistingsweb.services.ViewListingByDateOfBirthService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.PublishedDeceasedListingTest.ctx;
import static com.cemeterylistingswebtest.test.domain.PublishedDeceasedListingTest.repoList;
import static com.cemeterylistingswebtest.test.domain.PublishedDeceasedListingTest.subRepo;
import static com.cemeterylistingswebtest.test.domain.PublishedDeceasedListingTest.userRepo;
import static com.cemeterylistingswebtest.test.services.LoginServiceTest.ctx;
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
public class ViewListingByDateOfBirthServiceTest {
    
    public ViewListingByDateOfBirthServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long subID, listingId;
    public static ApplicationContext ctx;
    public static SubscriberRepository subRepo;
    public static UserRoleRepository userRepo;
    public static PublishedDeceasedListingRepository repoList;
    public ViewListingByDateOfBirthService dateServ;
     
    @Test(enabled = true)
     public void Test() {
        dateServ = ctx.getBean(ViewListingByDateOfBirthService.class);
        repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
         subRepo = ctx.getBean(SubscriberRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2001);
         calendar.set(Calendar.MONTH, Calendar.MARCH);
         calendar.set(Calendar.DATE, 17);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(2)
                 .build();
         //userRepo.save(userRole);
         //userRoleID = userRole.getUserRoleID();
         
         //Initialise subscriber
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("jGordan@gmail.com")
                .setFirstName("james")
                .setSurname("gordan")
                .setPwd("gotham")
                .setUsername("commish")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
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
         
         repoList.save(newListing);
         listingId=newListing.getPublishedListingID();
         
         List<PublishedDeceasedListing> pubListDob = dateServ.findListingByDOB("27/07/1985");
         Assert.assertFalse(pubListDob.isEmpty());
         repoList.delete(listingId);
         subRepo.delete(subID);
         
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
