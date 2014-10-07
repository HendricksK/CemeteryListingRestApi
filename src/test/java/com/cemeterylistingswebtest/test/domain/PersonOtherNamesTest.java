/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.PersonOtherNames;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PersonOtherNamesRepository;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.UserRoleTest.ctx;
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
public class PersonOtherNamesTest {
    
    public PersonOtherNamesTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    private static Long id, idN1, idN2;
    public static ApplicationContext ctx;
    public static PersonOtherNamesRepository repoNames;
    public static PublishedDeceasedListingRepository repoListing;
    
     @Test
     public void create() {
         System.out.println("Person Other Names Test");
         
         repoNames = ctx.getBean(PersonOtherNamesRepository.class);         
         repoListing = ctx.getBean(PublishedDeceasedListingRepository.class);
         
         //create published listing object
         PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("John")
                 .build();
         
         repoListing.save(newListing);
         id = newListing.getPublishedListingID();
         //create other names for the listing object
         PersonOtherNames newNames1 = new PersonOtherNames.Builder()
                 .setNames("Samual")
                 .setPublishedListingID(id)
                 .build();    
         
         PersonOtherNames newNames2 = new PersonOtherNames.Builder()
                 .setNames("Phillips")
                 .setPublishedListingID(id)
                 .build();  
         
        repoNames.save(newNames1);   
        repoNames.save(newNames2); 
        
        idN1 = newNames1.getId();
        idN2 = newNames2.getId();
     }
    
    @Test(dependsOnMethods="create")
     public void read(){
         repoNames = ctx.getBean(PersonOtherNamesRepository.class);         
         //repoListing = ctx.getBean(PublishedDeceasedListingRepository.class);
         
         Assert.assertEquals(repoNames.findOne(idN1).getNames(), "Samual");
         Assert.assertEquals(repoNames.findOne(idN2).getNames(), "Phillips");
     }
     
     @Test(dependsOnMethods="read")
     public void update(){
         repoNames = ctx.getBean(PersonOtherNamesRepository.class);         
         
         PersonOtherNames oldNames1 = repoNames.findOne(idN1);
         
         PersonOtherNames updateN1 = new PersonOtherNames.Builder()
                 .PersonOtherNames(oldNames1)
                 .setNames("Sam")
                 .build();
         
         
         repoNames.save(updateN1);         
         
         idN1 = updateN1.getId();
         
     }
     
     @Test(dependsOnMethods="update")
     public void delete(){
         repoNames = ctx.getBean(PersonOtherNamesRepository.class);      
         repoListing = ctx.getBean(PublishedDeceasedListingRepository.class);
         
         repoNames.delete(repoNames.findOne(idN1));
         repoNames.delete(repoNames.findOne(idN2));
         repoListing.delete(repoListing.findOne(id));
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
