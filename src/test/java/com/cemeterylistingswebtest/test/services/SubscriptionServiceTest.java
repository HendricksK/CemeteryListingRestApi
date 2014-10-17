/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.services;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.AdminRegisterSubscriberService;
import com.cemeterylistingsweb.services.SubscriptionService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.SubscriberTest.repo;
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
     private static Long id, id2, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static UserRoleRepository userRepo; 
    public  SubscriptionService subServ;
    public AdminRegisterSubscriberService adserv;
    
    @Test
    public void createSub(){
         repo = ctx.getBean(SubscriberRepository.class);
         adserv = ctx.getBean(AdminRegisterSubscriberService.class);
         
         
          Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2014);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 9);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
                 
         UserRole user = new UserRole.Builder()
                 .setLevel(2)
                 .build();
         
         //userRepo.save(user);
         //userRoleID = user.getUserRoleID();
         
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("bull@horse.com")
                .setFirstName("red")
                .setSurname("bull")
                .setPwd("wings")
                .setUsername("redBull")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(user)
                .build();
            
         
         repo.save(newSub);
         id = newSub.getSubscriberID();
         
        Assert.assertFalse(repo.findAll().isEmpty());
        
    }
     
     
     @Test(enabled=false)
     public void updateSubscription(){
         repo = ctx.getBean(SubscriberRepository.class);
         subServ = ctx.getBean(SubscriptionService.class);
         
         Subscriber oldSub = repo.findOne(id);
         
         Subscriber update = new Subscriber.Builder()
                 .Subscriber(oldSub)
                 .build();
         
        //repo.delete(repo.findOne(id));
        repo.save(update);
        
        id2 = update.getSubscriberID();
        subServ.updateSubscriber(id);
        Assert.assertTrue(true);
     }
     
     @Test(enabled=true)
     public void deleteSubsctription(){
         repo = ctx.getBean(SubscriberRepository.class); 
         userRepo = ctx.getBean(UserRoleRepository.class);
         subServ = ctx.getBean(SubscriptionService.class);
        
         
         //userRepo.delete(userRepo.findOne(userRoleID)); //cant delete this here because it is deleted in the update, so do we add it somehow in the update, need to take a break though
         subServ.deleteSubscriber(id);
         //subServ.deleteSubscriber(id2);
         //repo.delete(null);
         
         Assert.assertFalse(repo.exists(id));
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
