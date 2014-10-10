/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.Calendar;
import junit.framework.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ME
 */
public class PublishedDeceasedListingTest {
    
    public PublishedDeceasedListingTest() {
    }
    
   private static Long id, subID, userRoleID;
    public static ApplicationContext ctx;
    public static PublishedDeceasedListingRepository repoList;
    public static SubscriberRepository subRepo;
    public static UserRoleRepository userRepo;
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void create() {
         repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
     
         System.out.println("Published Deceased Listings Test");
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
         id = newListing.getPublishedListingID();
     }
     
     @Test(dependsOnMethods="create")
     public void read(){
         repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
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
         repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
         PublishedDeceasedListing oldListing = repoList.findOne(id);
         
         PublishedDeceasedListing updateListing = new PublishedDeceasedListing.Builder()
                 .PublishedDeceasedListing(oldListing)
                 .setSurname("Smith")
                 .build();
         
         repoList.save(updateListing);
         
         id = updateListing.getPublishedListingID();
         
     }
     @Test(dependsOnMethods="update")
     public void readUpdated(){
         repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
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
         repoList = ctx.getBean(PublishedDeceasedListingRepository.class);
         subRepo = ctx.getBean(SubscriberRepository.class);
         
         repoList.delete(id);
         subRepo.delete (subRepo.findOne(subID));
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
