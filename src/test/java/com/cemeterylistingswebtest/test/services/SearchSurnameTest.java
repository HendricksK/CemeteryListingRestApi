/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.services.SearchSurname;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.ArrayList;
import java.util.List;
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
 * @author Zaakir
 */
public class SearchSurnameTest {
    
    public SearchSurnameTest() {
    }

    private static Long id;
    public static ApplicationContext ctx;
    public SearchSurname serv;

    PublishedDeceasedListingRepository deadRepo;
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test(enabled = true)
    public void Test() {
        
        deadRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
        serv = ctx.getBean(SearchSurname.class);

        //create published listings
        PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("jimmy")
                 .setSurname("hendricks")
                 
                 .setGender("Male")
                 .setDateOfBirth("18/11/1975")
                 .setDateOfDeath("10/02/2002")
                 .setGraveInscription("shredder of guitar")
                 .setGraveNumber("2852")
                 .setImageOfBurialSite("/images/011.jpg")
                 .setLastKnownContactName("jack")
                 .setLastKnownContactNumber("0738476492")
                 //.setCemeteryID(newCemetery.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
        
                 PublishedDeceasedListing newListing2 = new PublishedDeceasedListing.Builder()
                 .setFirstName("Walter")
                 .setSurname("White")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1979")
                 .setDateOfDeath("14/02/2012")
                 .setGraveInscription("Evilest person eva")
                 .setGraveNumber("2442")
                 .setImageOfBurialSite("/images/002.jpg")
                 .setLastKnownContactName("jesse")
                 .setLastKnownContactNumber("0725456381")
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
                 .setDateOfBirth("08/06/1968")
                 .setDateOfDeath("14/02/2000")
                 .setGraveInscription("meh")
                 .setGraveNumber("2816")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("luke")
                 .setLastKnownContactNumber("0725554482")
                 //.setCemeteryID(newCemetery2.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing4 = new PublishedDeceasedListing.Builder()
                 .setFirstName("Bruce")
                 .setSurname("Wayne")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1965")
                 .setDateOfDeath("14/02/2008")
                 .setGraveInscription("Batman")
                 .setGraveNumber("2556")
                 .setImageOfBurialSite("/images/001.jpg")
                 
                 //.setCemeteryID(newCemetery3.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
         deadRepo.save(newListing2);
         deadRepo.save(newListing3);
         deadRepo.save(newListing4);
         
        //call service
         List<PublishedDeceasedListing> deceasedList = deadRepo.findAll();

        List<PublishedDeceasedListing> names = new ArrayList();
         names = serv.getAllSurname("Wayne");
         
        Assert.assertEquals(names.size(), 1);
        deadRepo.delete(newListing);
        deadRepo.delete(newListing2);
        deadRepo.delete(newListing3);
        deadRepo.delete(newListing4);
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
