/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.domain;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
import java.util.Calendar;
import java.util.Date;
import junit.framework.Assert;
import org.apache.derby.iapi.types.SQLDate;
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
 * @author Kurvin Hendricks
 */
public class SubscriberTest {
    
    public SubscriberTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    private static Long id, userRoleID;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static UserRoleRepository userRepo;
    
    
    
     @Test
     public void create() {
         System.out.println("Subscriber Test");
         
         repo = ctx.getBean(SubscriberRepository.class);
         userRepo = ctx.getBean(UserRoleRepository.class);
         
          Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2008);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 4);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
                 
         UserRole user = new UserRole.Builder()
                 .setLevel(1)
                 .build();
         
         //userRepo.save(user);
         //userRoleID = user.getUserRoleID();
         
         Subscriber newSub = new Subscriber.Builder()
                .setEmail("manfredOsulivan@horseRaddish.com")
                .setFirstName("Manfred")
                .setSurname("Osulivan")
                .setPwd("jesus")
                .setUsername("ManiFredOssy")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(user)
                .build();
            
         
         repo.save(newSub);
         id = newSub.getSubscriberID();
     }
     
     @Test(dependsOnMethods="create")
     public void readNotNull(){  
         repo = ctx.getBean(SubscriberRepository.class);         
         Assert.assertNotNull(repo.findOne(id).getSubscriberID());
         
     }
     @Test(dependsOnMethods="readNotNull")
     public void read(){  
         repo = ctx.getBean(SubscriberRepository.class);        
         Assert.assertEquals(repo.findOne(id).getEmail(),"manfredOsulivan@horseRaddish.com");
         Assert.assertEquals(repo.findOne(id).getFirstName(),"Manfred");
         Assert.assertEquals(repo.findOne(id).getSurname(),"Osulivan");
         Assert.assertEquals(repo.findOne(id).getPwd(),"jesus");
         Assert.assertEquals(repo.findOne(id).getUsername(),"ManiFredOssy");
     }
     
     
     @Test(dependsOnMethods="read")
     public void testDate(){
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.YEAR, 2008);
         calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
         calendar.set(Calendar.DATE, 4);
         java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         repo = ctx.getBean(SubscriberRepository.class);
         
         Calendar cal = Calendar.getInstance();
         cal.setTime(javaSqlDate);
         int year =  cal.get(Calendar.YEAR);
         int month =  cal.get(Calendar.MONTH);
         int day =  cal.get(Calendar.DAY_OF_MONTH);
         Assert.assertEquals(year, 2008);
         Assert.assertEquals(month, 01); //counts from 0
         Assert.assertEquals(day, 4);
     }
     
     @Test(dependsOnMethods="testDate")
     public void update(){
         repo = ctx.getBean(SubscriberRepository.class);
         
         Subscriber oldSub = repo.findOne(id);
         
         Subscriber update = new Subscriber.Builder()
                 .Subscriber(oldSub)
                 .build();
         
        //repo.delete(repo.findOne(id));
        repo.save(update);
        id = update.getSubscriberID();
        //userRoleID = update.getUserRoleID().getUserRoleID();
     }
     
     @Test(dependsOnMethods="update")
     public void delete(){
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
