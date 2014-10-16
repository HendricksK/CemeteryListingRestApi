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
import com.cemeterylistingsweb.services.ViewListingByDateOfBirthService;
import com.cemeterylistingsweb.services.ViewListingByDateOfDeathService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.ViewListingByDateOfBirthServiceTest.ctx;
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
public class ViewListingByDateOfDeathServiceTest {
    
    public ViewListingByDateOfDeathServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     private  Long subID;
    public static ApplicationContext ctx;
    public static SubscriberRepository subRepo;
    public static UserRoleRepository userRepo;
    public static PublishedDeceasedListingRepository repoList;
    public ViewListingByDateOfDeathService dateServ;
     
    
     
     @Test(enabled = false)
     public void TestDoD() {
        dateServ = ctx.getBean(ViewListingByDateOfDeathService.class);
        repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
         subRepo = ctx.getBean(SubscriberRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2008);
         calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
         calendar.set(Calendar.DATE, 4);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(1)
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
         subRepo.save(newSub);
         subID = newSub.getSubscriberID();
         
         
         
         PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
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
                 .build();
         
         repoList.save(newListing);
         
         List<PublishedDeceasedListing> pubListDod = dateServ.findListingByDOD("14/02/2005");
         Assert.assertFalse(pubListDod.isEmpty());
         repoList.delete(newListing);
         
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
