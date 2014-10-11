/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.services.ViewListingByLocationService;
import com.cemeterylistingsweb.services.SearchSurname;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.SearchSurnameTest.ctx;
import java.util.ArrayList;
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
 * @author Zaakir
 */
public class ViewListingByLocationServiceTest {
    
    public ViewListingByLocationServiceTest() {
    }
private static Long id;
    public static ApplicationContext ctx;
    
    PublishedDeceasedListingRepository deadRepo;
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test(enabled = false)
    public void Test() {
        
        deadRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
        
        
        //create published listings
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
                 .setCemeteryID(12345l)
                 //.setCemeteryID(newCemetery.getId())
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
                 .setCemeteryID(12345l)
                //.setCemeteryID(newCemetery.getId())
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
                 .setCemeteryID(12345l)
                 //.setCemeteryID(newCemetery2.getId())
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
                 .setCemeteryID(12346l) 
                    //.setCemeteryID(newCemetery3.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
         deadRepo.save(newListing2);
         deadRepo.save(newListing3);
         deadRepo.save(newListing4);
        
         
         
       ViewListingByLocationService service;
         service = ctx.getBean(ViewListingByLocationService.class);
         
        List<PublishedDeceasedListing> deceased = new ArrayList();
         deceased = service.getAllDeceased(12345l);
         
        Assert.assertEquals(deceased.size(), 3);
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
