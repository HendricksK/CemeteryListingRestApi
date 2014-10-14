/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.services.ViewListingByCemetery;
import com.cemeterylistingsweb.services.ViewListingByMaidenNameService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.ViewListingByCemeteryTest.ctx;
import static com.cemeterylistingswebtest.test.services.ViewListingByCemeteryTest.deadRepo;
import static com.cemeterylistingswebtest.test.services.ViewListingByCemeteryTest.repo;
import java.util.List;
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
public class ViewListingByMaidenNameTest {
    
    public ViewListingByMaidenNameTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id, id2, id3;
    public static ApplicationContext ctx;
    
    public static PublishedDeceasedListingRepository deadRepo;
    public ViewListingByMaidenNameService maidServ;
     
    @Test(enabled=false)
     public void hello() {
         maidServ = ctx.getBean(ViewListingByMaidenNameService.class);
        
        deadRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
        
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
                 .setCemeteryID(id)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
        
                 PublishedDeceasedListing newListing2 = new PublishedDeceasedListing.Builder()
                 .setFirstName("Walter")
                 .setSurname("White")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1969")
                 .setDateOfDeath("14/02/2005")
                 .setGraveInscription("Evilest person eva")
                 .setGraveNumber("2442")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berrys")
                 .setLastKnownContactNumber("0725456482")
                 .setCemeteryID(id)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing3 = new PublishedDeceasedListing.Builder()
                 .setFirstName("Leia")
                 .setSurname("Skywalker")
                 .setMaidenName("Kanobi")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1969")
                 .setDateOfDeath("14/02/2005")
                 .setGraveInscription("meh")
                 .setGraveNumber("2816")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berry")
                 .setLastKnownContactNumber("0725554482")
                 .setCemeteryID(id2)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing4 = new PublishedDeceasedListing.Builder()
                 .setFirstName("Bruce")
                 .setSurname("Wayne")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1969")
                 .setDateOfDeath("14/02/2005")
                 .setGraveInscription("Batman")
                 .setGraveNumber("2556")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berry")
                 .setLastKnownContactNumber("0725676482")
                 .setCemeteryID(id3)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
         deadRepo.save(newListing2);
         deadRepo.save(newListing3);
         deadRepo.save(newListing4);
         
          List<PublishedDeceasedListing> deceasedList = deadRepo.findAll();
            Assert.assertEquals(deceasedList.isEmpty(), false);
           List<PublishedDeceasedListing> maidenNameList = maidServ.findListingByMaidenName("Kanobi");
            Assert.assertEquals(deceasedList.size(), 1);
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
