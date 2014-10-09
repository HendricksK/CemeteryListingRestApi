/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.SubscriptionService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.Calendar;
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
 * @author Ryno
 */
public class SubscriptionServiceTest {
    
    public SubscriptionServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     private static Long id, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static UserRoleRepository userRepo; 
    public  SubscriptionService subServ;
    
    
     @Test
     public void registerSubscriber() {
            subServ = ctx.getBean(SubscriptionService.class);
            repo = ctx.getBean(SubscriberRepository.class);
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2008);
            calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
            calendar.set(Calendar.DATE, 4);
        
            java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
          
            subServ.registerSubscriber("manfredOsulivan@horseRaddish.com", "Manfred", "Osulivan", "ManiFredOssy", "jesus",javaSqlDate , 2);
            List listAll = subServ.findAll();
            Subscriber sub = (Subscriber) listAll.get(0);
            id=sub.getSubscriberID();
            Assert.assertFalse(repo.findAll().isEmpty());
            
            
     }
     
     @Test
     public void updateSubscription(){
         repo = ctx.getBean(SubscriberRepository.class);
         
         Subscriber oldSub = repo.findOne(id);
         
         Subscriber update = new Subscriber.Builder()
                 .Subscriber(oldSub)
                 .build();
         
        //repo.delete(repo.findOne(id));
        repo.save(update);
        id = update.getSubscriberID();
     }
     
     @Test
     public void deleteSubsctription(){
         repo = ctx.getBean(SubscriberRepository.class); 
         userRepo = ctx.getBean(UserRoleRepository.class);
         
         //userRepo.delete(userRepo.findOne(userRoleID)); //cant delete this here because it is deleted in the update, so do we add it somehow in the update, need to take a break though
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
