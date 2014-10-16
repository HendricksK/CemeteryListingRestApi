/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.LoginService;
import com.cemeterylistingsweb.services.CemeteryRegistrationService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.LoginServiceTest.ctx;
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
 * @author Ryno
 */
public class CemeteryRegistrationServiceTest {
    
    public CemeteryRegistrationServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static CemeteryRepository cemRepo;
    public CemeteryRegistrationService regServ;
    
     @Test
     public void registerCemetery(){
          regServ = ctx.getBean(CemeteryRegistrationService.class);
          cemRepo = ctx.getBean(CemeteryRepository.class);
          
          /*Location local = new Location.Builder()
                 .setCemeteryName("Palm Springs")
                 .setCountry("America")
                 .setDistrict_state("Washington")
                 .setLocationOfCemetery("12.06.12:45.63.89")
                 .setProvince_State("New Jersey")
                 .setTown("Marlboro")
                 .build();*/
          
          //All methods of creating location/cemetery work
          Location locals = regServ.registerLocation("Palm Springs", "America", "Washington", "12.06.12:45.63.89", "New Jersey", "Marlboro");
          Long cemID = regServ.registerCemeteryReturn(locals, "jack daniels", "0215917865");
          //regServ.registerCemeteryLocation("Palm Springs", "America", "Washington", "12.06.12:45.63.89", "New Jersey", "Marlboro","jack daniels", "0215917865");
          
          Assert.assertNotNull(cemRepo.findOne(cemID).getId());
          cemRepo.delete(cemID);
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
