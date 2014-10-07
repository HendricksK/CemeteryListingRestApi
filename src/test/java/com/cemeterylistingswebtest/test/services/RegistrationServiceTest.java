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
import com.cemeterylistingsweb.services.RegistrationService;
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
public class RegistrationServiceTest {
    
    public RegistrationServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static CemeteryRepository cemRepo;
    public RegistrationService regServ;
     @Test
     public void registerSubscriber() {
            regServ = ctx.getBean(RegistrationService.class);
            repo = ctx.getBean(SubscriberRepository.class);
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2008);
            calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
            calendar.set(Calendar.DATE, 4);
        
            java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
          
            regServ.registerSubscriber("manfredOsulivan@horseRaddish.com", "Manfred", "Osulivan", "ManiFredOssy", "jesus",javaSqlDate , 2);
            Assert.assertFalse(repo.findAll().isEmpty());
            
     }
     
     @Test
     public void registerCemetery(){
          regServ = ctx.getBean(RegistrationService.class);
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
          regServ.registerCemetery(locals, "jack daniels", "0215917865");
          //regServ.registerCemeteryLocation("Palm Springs", "America", "Washington", "12.06.12:45.63.89", "New Jersey", "Marlboro","jack daniels", "0215917865");
          
          Assert.assertFalse(cemRepo.findAll().isEmpty());
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
