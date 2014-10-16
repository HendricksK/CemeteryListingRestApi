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
import com.cemeterylistingsweb.services.ViewListingByLocationService;
import com.cemeterylistingsweb.services.SearchSurname;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.SearchSurnameTest.ctx;
import static com.cemeterylistingswebtest.test.services.ViewListingByCemeteryTest.ctx;
import static com.cemeterylistingswebtest.test.services.ViewListingByCemeteryTest.repo;
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
private static Long id, cemId, cemId2;
    public static ApplicationContext ctx;
    public ViewListingByLocationService service;
    public PublishedDeceasedListingRepository deadRepo;
    public CemeteryRepository repo;
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test(enabled = true)
    public void Test() {
        service = ctx.getBean(ViewListingByLocationService.class);
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
          Cemetery newCem = new Cemetery.Builder()
                 .setContactName("that other place")
                 .setContactNumber("0215699175")
                 .setLocation(local)
                 .build();
          
         repo.save(newCemetery);
         repo.save(newCem);
         cemId = newCemetery.getId();
         cemId2 = newCemetery.getId();
        
        //create published listings
        PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("clark")
                 .setSurname("kent")
                
                 .setGender("Male")
                 .setDateOfBirth("12/03/1987")
                 .setDateOfDeath("14/08/2009")
                 .setGraveInscription("is really superman")
                 .setGraveNumber("2856")
                 .setImageOfBurialSite("/images/005.jpg")
                 
                 
                 .setCemeteryID(cemId)
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
        
                 PublishedDeceasedListing newListing2 = new PublishedDeceasedListing.Builder()
                 .setFirstName("chuck")
                 .setSurname("norris")
                 .setGender("Male")
                 .setDateOfBirth("27/10/1979")
                 .setDateOfDeath("11/06/2000")
                 .setGraveInscription("roundhouse kicked death")
                 .setGraveNumber("2852")
                 .setImageOfBurialSite("/images/011.jpg")
                 
                 .setCemeteryID(cemId)
                //.setCemeteryID(newCemetery.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing3 = new PublishedDeceasedListing.Builder()
                 .setFirstName("amanda")
                 .setSurname("walker")
                 .setMaidenName("doe")
                 .setGender("Female")
                 .setDateOfBirth("05/09/1981")
                 .setDateOfDeath("19/04/2006")
                 .setGraveInscription("liked cat videos")
                 .setGraveNumber("2815")
                 .setImageOfBurialSite("/images/009.jpg")
                 
                 .setCemeteryID(cemId)
                 //.setCemeteryID(newCemetery2.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
                 
                 PublishedDeceasedListing newListing4 = new PublishedDeceasedListing.Builder()
                 .setFirstName("The")
                 .setSurname("stig")
                 .setGender("Male")
                 .setDateOfBirth("01/01/1974")
                 .setDateOfDeath("15/05/2007")
                 .setGraveInscription("all we know is ...")
                 .setGraveNumber("2796")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berry")
                 .setLastKnownContactNumber("0725676482")
                 .setCemeteryID(cemId2) 
                    //.setCemeteryID(newCemetery3.getId())
                 //subscriberApprovedID
                 //subscriber submitted id
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
         deadRepo.save(newListing2);
         deadRepo.save(newListing3);
         deadRepo.save(newListing4);

        List<PublishedDeceasedListing> deceased = new ArrayList();
         deceased = service.getAllDeceased(cemId);
         
        Assert.assertEquals(deceased.size(), 3);
        deadRepo.delete(newListing);
        deadRepo.delete(newListing2);
        deadRepo.delete(newListing3);
        deadRepo.delete(newListing4);
        repo.delete(newCemetery);
        repo.delete(newCem);
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
