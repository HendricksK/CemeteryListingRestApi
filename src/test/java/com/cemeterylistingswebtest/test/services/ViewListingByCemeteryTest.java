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
import com.cemeterylistingsweb.services.CemeteryListingService;
import com.cemeterylistingsweb.services.ViewListingByCemetery;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.cemeteryListingsServiceTest.ctx;
import static com.cemeterylistingswebtest.test.services.cemeteryListingsServiceTest.deadRepo;
import static com.cemeterylistingswebtest.test.services.cemeteryListingsServiceTest.repo;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
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
public class ViewListingByCemeteryTest {
    
    public ViewListingByCemeteryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id, id2, id3;
    public static ApplicationContext ctx;
    public static CemeteryRepository repo;
    public static PublishedDeceasedListingRepository deadRepo;
    public ViewListingByCemetery cemListServ;
    
    @Test(enabled = false)
    public void Test() {
        cemListServ = ctx.getBean(ViewListingByCemetery.class);
        repo = ctx.getBean(CemeteryRepository.class);
        deadRepo = ctx.getBean(PublishedDeceasedListingRepository.class);
        
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
         
         Location local2 = new Location.Builder()
                 .setCemeteryName("Dante's Inferno")
                 .setCountry("America")
                 .setDistrict_state("Denver")
                 .setLocationOfCemetery("12.06.12:45.63.89")
                 .setProvince_State("Colorado")
                 .setTown("Boulder")
                 .build();
         
         Cemetery newCemetery2 = new Cemetery.Builder()
                 .setContactName("Dante's Inferno")
                 .setContactNumber("0215645682")
                 .setLocation(local2)
                 .build();
         
         Location local3 = new Location.Builder()
                 .setCemeteryName("Mons Vaticanus")
                 .setCountry("America")
                 .setDistrict_state("Tennessee")
                 .setLocationOfCemetery("12.06.12:45.63.89")
                 .setProvince_State("Nashville")
                 .setTown("Dickson")
                 .build();
         
         Cemetery newCemetery3 = new Cemetery.Builder()
                 .setContactName("Mons Vaticanus")
                 .setContactNumber("02434838412")
                 .setLocation(local3)
                 .build();
     
        repo.save(newCemetery);
        repo.save(newCemetery2);
        repo.save(newCemetery3);
        id = newCemetery.getId();
        id2 = newCemetery2.getId();
        id3= newCemetery3.getId();
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
         
        //call service
        // cemListServ.persist(newCemetery);
         
         List<PublishedDeceasedListing> deceasedList = deadRepo.findAll();
         Assert.notEmpty(deceasedList);
         List<Cemetery> listAllrepo = repo.findAll();
         Assert.notEmpty(listAllrepo);
         List<Cemetery> listAll = cemListServ.findAll();
         Assert.notEmpty(listAll);
         List<PublishedDeceasedListing> lists = cemListServ.findListingByCemetery(id);
         Assert.notEmpty(lists);
         
         //removal
         repo.delete(repo.findOne(id));
         repo.delete(repo.findOne(id2));
         repo.delete(repo.findOne(id3));
         deadRepo.delete(newListing);
         deadRepo.delete(newListing2);
         deadRepo.delete(newListing3);
         deadRepo.delete(newListing4);
         //repo.delete(repo.findOne(id));
         
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
