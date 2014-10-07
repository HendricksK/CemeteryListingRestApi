/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.PersonOtherNamesTest.ctx;
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
 * @author Kurvin Hendricks
 */
public class RequiresApprovalDeceasedListingTest {
    
    public RequiresApprovalDeceasedListingTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id, subID, userRoleID;
    public static ApplicationContext ctx;
    public static RequiresApprovalDeceasedListingRepository repoList;
    public static SubscriberRepository subRepo;
    public static UserRoleRepository userRepo;
    
     @Test
     public void create() {
          System.out.println("Requires Approval Deceased Listing Test");
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
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
     }
     
     @Test(dependsOnMethods="create")
     public void read(){
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         Assert.assertEquals(repoList.findOne(id).getFirstName(), "Hendrika");
         Assert.assertEquals(repoList.findOne(id).getSurname()  , "Fourie" );
         Assert.assertEquals(repoList.findOne(id).getMaidenName()  , "Gerber" );
         Assert.assertEquals(repoList.findOne(id).getGender()  , "Female" );
         Assert.assertEquals(repoList.findOne(id).getDateOfBirth()  , "08/06/1969" );
         Assert.assertEquals(repoList.findOne(id).getDateOfDeath()  , "14/02/2005" );
         Assert.assertEquals(repoList.findOne(id).getGraveInscription()  , "Hippiest person eva" );
         Assert.assertEquals(repoList.findOne(id).getGraveNumber()  , "2456" );
         Assert.assertEquals(repoList.findOne(id).getImageOfBurialSite()  , "/images/001.jpg" );
         Assert.assertEquals(repoList.findOne(id).getLastKnownContactName()  ,"Berry"  );
         Assert.assertEquals(repoList.findOne(id).getLastKnownContactNumber()  , "0725576482" );
         Assert.assertNotNull(repoList.findOne(id).getSubscriberSubmitID());
     }
     
     @Test(dependsOnMethods="read")
     public void update(){
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         
         RequiresApprovalDeceasedListing oldListing = repoList.findOne(id);
         
         RequiresApprovalDeceasedListing updateListing = new RequiresApprovalDeceasedListing.Builder()
                 .RequiresApprovalDeceasedListing(oldListing)
                 .setSurname("Smith")
                 .build();
         
         repoList.save(updateListing);
         
         id = updateListing.getRequiresApprovalDeceasedListingID();
     }
     @Test(dependsOnMethods="update")
     public void readUpdated(){
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         Assert.assertEquals(repoList.findOne(id).getFirstName(), "Hendrika");
         Assert.assertEquals(repoList.findOne(id).getSurname()  , "Smith" );
         Assert.assertEquals(repoList.findOne(id).getMaidenName()  , "Gerber" );
         Assert.assertEquals(repoList.findOne(id).getGender()  , "Female" );
         Assert.assertEquals(repoList.findOne(id).getDateOfBirth()  , "08/06/1969" );
         Assert.assertEquals(repoList.findOne(id).getDateOfDeath()  , "14/02/2005" );
         Assert.assertEquals(repoList.findOne(id).getGraveInscription()  , "Hippiest person eva" );
         Assert.assertEquals(repoList.findOne(id).getGraveNumber()  , "2456" );
         Assert.assertEquals(repoList.findOne(id).getImageOfBurialSite()  , "/images/001.jpg" );
         Assert.assertEquals(repoList.findOne(id).getLastKnownContactName()  ,"Berry"  );
         Assert.assertEquals(repoList.findOne(id).getLastKnownContactNumber()  , "0725576482" );
         Assert.assertNotNull(repoList.findOne(id).getSubscriberSubmitID());
     }
     @Test(dependsOnMethods="readUpdated")
     public void delete(){
         repoList = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
         subRepo = ctx.getBean(SubscriberRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
        // userRepo.delete(userRepo.findOne(userRoleID)); //cant delete this here because it is deleted in the update, so do we add it somehow in the update, need to take a break though
         subRepo.delete (subRepo.findOne(subID));
         repoList.delete(repoList.findOne(id));
         
         
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
