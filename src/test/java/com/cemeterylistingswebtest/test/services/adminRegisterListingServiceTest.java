/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.AdminRegisterListingService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.services.ViewListingsBySubscriberServiceTest.ctx;
import static com.cemeterylistingswebtest.test.services.ViewListingsBySubscriberServiceTest.subRepo;
import static com.cemeterylistingswebtest.test.services.ViewListingsBySubscriberServiceTest.userRepo;
import java.util.Calendar;
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
public class adminRegisterListingServiceTest {
    
    public adminRegisterListingServiceTest() {
    }
    Long id, subID;
  RequiresApprovalDeceasedListingRepository deadRepo;
 public static ApplicationContext ctx;

 public AdminRegisterListingService adserv;
 
 @Test
  public void Test() {
      
  deadRepo = ctx.getBean(RequiresApprovalDeceasedListingRepository.class);
  adserv = ctx.getBean(AdminRegisterListingService.class);
  subRepo = ctx.getBean(SubscriberRepository.class);
  userRepo = ctx.getBean(UserRoleRepository.class);
         
         //Initialise date
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2008);
         calendar.set(Calendar.MONTH, Calendar.MARCH);
         calendar.set(Calendar.DATE, 5);
          
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
         //Initialise date
         Calendar calendar2 = Calendar.getInstance();
         calendar2.set(Calendar.YEAR, 2014);
         calendar2.set(Calendar.MONTH, Calendar.JUNE);
         calendar2.set(Calendar.DATE, 20);
          
         java.sql.Date validDate = new java.sql.Date(calendar2.getTime().getTime());
         
         //Initialise user role                
         UserRole userRole = new UserRole.Builder()
                 .setLevel(2)
                 .build();
  
  
  Subscriber newSub = new Subscriber.Builder()
                .setEmail("jackieChan@yahoo.com")
                .setFirstName("jackie")
                .setSurname("Chan")
                .setPwd("whaa")
                .setUsername("jChan")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(userRole)
                 .setValidUntil(validDate)
                .build();
         subRepo.save(newSub);
         subID = newSub.getSubscriberID();
      
     
        long listId = adserv.registerDeceasedListingReturn("zaakir", "arendse", "maiden", "male", "date1", "date2", "blah", "27", "url", "zaakir27", "0474754", subID);
      
       Assert.assertFalse(deadRepo.findAll().isEmpty());
       subRepo.delete(newSub);
       deadRepo.delete(listId);
       
      
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
