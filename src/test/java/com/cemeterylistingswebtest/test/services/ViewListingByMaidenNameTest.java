/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Location;
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
    private static Long id, id2, id3, cemId;
    public static ApplicationContext ctx;
    
    public static PublishedDeceasedListingRepository deadRepo;
    public ViewListingByMaidenNameService maidServ;
    public static CemeteryRepository repo;
     
    @Test(enabled=true)
     public void hello() {
         maidServ = ctx.getBean(ViewListingByMaidenNameService.class);
        deadRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
        repo = ctx.getBean(CemeteryRepository.class);
        
        //create cemetery
        Location local = new Location.Builder()
                 .setCemeteryName("Palm Springs")
                 .setCountry("America")
                 .setDistrict_state("Washington")
                 .setLocationOfCemetery("12.06.12:45.63.89")
                 .setProvince_State("New Jersey")
                 .setTown("Marlboro")
                 .build();
         
         Cemetery newCemetery = new Cemetery.Builder()
                 .setContactName("Palm Springs")
                 .setContactNumber("0215698412")
                 .setLocation(local)
                 .build();
          repo.save(newCemetery);
          cemId = newCemetery.getId();
          
        PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("sally")
                 .setSurname("white")
                 .setMaidenName("black")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1980")
                 .setDateOfDeath("19/02/2014")
                 .setGraveInscription("dunno who this was")
                 .setGraveNumber("2786")
                 .setImageOfBurialSite("/images/006.jpg")
                 
                 .setCemeteryID(cemId)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
        
                 PublishedDeceasedListing newListing2 = new PublishedDeceasedListing.Builder()
                 .setFirstName("gary")
                 .setSurname("medici")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1983")
                 .setDateOfDeath("14/02/2001")
                 .setGraveInscription("person")
                 .setGraveNumber("2832")
                 .setImageOfBurialSite("/images/001.jpg")
                 
                 .setCemeteryID(cemId)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing3 = new PublishedDeceasedListing.Builder()
                 .setFirstName("kia")
                 .setSurname("picanto")
                 .setMaidenName("panna")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1978")
                 .setDateOfDeath("14/02/2007")
                 .setGraveInscription("meh")
                 .setGraveNumber("2126")
                 .setImageOfBurialSite("/images/001.jpg")
                 
                 .setCemeteryID(cemId)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing4 = new PublishedDeceasedListing.Builder()
                 .setFirstName("benny")
                 .setSurname("michaels")
                 .setGender("Male")
                 .setDateOfBirth("08/06/1975")
                 .setDateOfDeath("14/02/2008")
                 .setGraveInscription("#5")
                 .setGraveNumber("2736")
                 .setImageOfBurialSite("/images/001.jpg")
                 
                 .setCemeteryID(cemId)
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
           List<PublishedDeceasedListing> maidenNameList = maidServ.findListingByMaidenName("panna");
            Assert.assertEquals(deceasedList.isEmpty(), false);
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
