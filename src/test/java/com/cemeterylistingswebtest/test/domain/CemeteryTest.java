/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
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
public class CemeteryTest {
    
    public CemeteryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    private static Long id;
    public static ApplicationContext ctx;
    public static CemeteryRepository repo;
    
     @Test
     public void create() {
        System.out.println("Cemetery Testing");
         repo = ctx.getBean(CemeteryRepository.class);
         
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
        id = newCemetery.getId();
     }
     
     @Test(dependsOnMethods="create")
     public void read(){
         repo = ctx.getBean(CemeteryRepository.class);
         Assert.assertEquals(repo.findOne(id).getContactName(), "Palm Springs");
         Assert.assertEquals(repo.findOne(id).getContactNumber(), "0215698412");
         
         
         Assert.assertEquals(repo.findOne(id).getLocationID().getCemeteryName(), "Palm Springs");
         Assert.assertEquals(repo.findOne(id).getLocationID().getCountry(), "America");
         Assert.assertEquals(repo.findOne(id).getLocationID().getDistrict_state(), "Washington");
         Assert.assertEquals(repo.findOne(id).getLocationID().getLocationOfCemetery(), "12.06.12:45.63.89");
         Assert.assertEquals(repo.findOne(id).getLocationID().getProvince_State(), "New Jersey");
         Assert.assertEquals(repo.findOne(id).getLocationID().getTown(), "Marlboro");
     }
     
     @Test(dependsOnMethods="read")
     public void update(){
         repo = ctx.getBean(CemeteryRepository.class);
         Cemetery oldCemetery = repo.findOne(id);
         Cemetery updateCemetery = new Cemetery.Builder()
                 .Cemetery(oldCemetery)
                 .setContactNumber("0215554412")
                 .build();
         
         repo.save(updateCemetery);
         id = updateCemetery.getId();
     }
     
      @Test(dependsOnMethods="update")
     public void readUpdated(){
         repo = ctx.getBean(CemeteryRepository.class);
         Assert.assertEquals(repo.findOne(id).getContactName(), "Palm Springs");
         Assert.assertEquals(repo.findOne(id).getContactNumber(), "0215554412");
         
         
         Assert.assertEquals(repo.findOne(id).getLocationID().getCemeteryName(), "Palm Springs");
         Assert.assertEquals(repo.findOne(id).getLocationID().getCountry(), "America");
         Assert.assertEquals(repo.findOne(id).getLocationID().getDistrict_state(), "Washington");
         Assert.assertEquals(repo.findOne(id).getLocationID().getLocationOfCemetery(), "12.06.12:45.63.89");
         Assert.assertEquals(repo.findOne(id).getLocationID().getProvince_State(), "New Jersey");
         Assert.assertEquals(repo.findOne(id).getLocationID().getTown(), "Marlboro");
     }
     
     
     
     @Test(dependsOnMethods="readUpdated")
     public void delete(){
         repo = ctx.getBean(CemeteryRepository.class);
         repo.delete(repo.findOne(id));         
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
