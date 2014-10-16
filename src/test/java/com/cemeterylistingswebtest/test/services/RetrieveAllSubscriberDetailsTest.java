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
import com.cemeterylistingsweb.services.RetrieveAllSubscriberDetails;
import com.cemeterylistingsweb.services.SubscriptionService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import static com.cemeterylistingswebtest.test.domain.SubscriberTest.repo;
import static com.cemeterylistingswebtest.test.services.SubscriptionServiceTest.ctx;
import static com.cemeterylistingswebtest.test.services.SubscriptionServiceTest.repo;
import java.util.Calendar;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
public class RetrieveAllSubscriberDetailsTest {
    
    public RetrieveAllSubscriberDetailsTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static UserRoleRepository userRepo; 
    public  RetrieveAllSubscriberDetails subServ;
    
     @Test
     public void hello() {
         subServ = ctx.getBean(RetrieveAllSubscriberDetails.class);
            repo = ctx.getBean(SubscriberRepository.class);
            
            Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2008);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 4);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
                 
         UserRole user = new UserRole.Builder()
                 .setLevel(2)
                 .build();
         
         //userRepo.save(user);
         userRoleID = user.getUserRoleID();
         
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("manfredOsulivan@horseRaddish.com")
                .setFirstName("willie")
                .setSurname("Wonker")
                .setPwd("charlie")
                .setUsername("wwonker")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(user)
                .build();
            
         
         repo.save(newSub);
         id = newSub.getSubscriberID();
         
         Subscriber sub = subServ.getSubscriberByUsername("wwonker");
         Assert.assertEquals(sub.getUsername(), "wwonker");
         
         repo.delete(id);
         
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
