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
import com.cemeterylistingsweb.services.LoginService;
import com.cemeterylistingswebtest.test.ConnectionConfigTest;
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
 * @author Ryno
 */
public class LoginServiceTest {
    
    public LoginServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    private static Long id;
    public static ApplicationContext ctx;
    public static SubscriberRepository repo;
    public static UserRoleRepository userRepo;
    public LoginService loginServ;
    
    @Test
    public void AuthenticateTest() {
        loginServ = ctx.getBean(LoginService.class);
        repo = ctx.getBean(SubscriberRepository.class);
        
        //create subscribers
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
         
         Assert.assertTrue(loginServ.authenticate(newSub.getUsername(), newSub.getPwd()), "Login successful");
         Assert.assertFalse(loginServ.authenticate(newSub.getUsername(), ""), "Login Failed; no password");
         Assert.assertFalse(loginServ.authenticate("", newSub.getPwd()), "Login Failed ; no username");
         Assert.assertFalse(loginServ.authenticate("", ""), "Login Failed no username or password");
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
