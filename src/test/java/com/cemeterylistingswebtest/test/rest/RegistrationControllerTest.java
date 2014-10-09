/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.rest;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.services.RegistrationService;
import static com.cemeterylistingswebtest.test.domain.SubscriberTest.repo;
import java.util.Calendar;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Zaakir
 */
public class RegistrationControllerTest {
    
    public RegistrationControllerTest() {
    }

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "http://localhost:8084/CemeteryListingsWebApp/";
    private RegistrationService cs;
    private Long id;

    @Test(enabled = false)
    public void testCreate() {
       System.out.println("Registration Testing");
         Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2008);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 4);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         
                 
         UserRole user = new UserRole.Builder()
                 .setLevel(1)
                 .build();
        
       Subscriber newSub = new Subscriber.Builder()
                .setEmail("zaakir@gmail.com")
                .setFirstName("zaakir")
                .setSurname("arendse")
                .setPwd("123")
                .setUsername("zak")
                .setSubscriptionDate(javaSqlDate)
                .setUserRoleID(user)
                .build();
        
        HttpEntity<Subscriber> requestEntity = new HttpEntity<>(newSub, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = 
        restTemplate.exchange(URL + "api/Registration/create", HttpMethod.POST, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        id = newSub.getSubscriberID();
    }
    
    @Test(enabled = false, dependsOnMethods = "testCreate")
    public void testClubUpdate() {
        // LEFT AS AN EXERCISE FOR YOU
        // GET THE CLUB and THEN CHANGE AND MAKE A COPY
        //THEN SEND TO THE SERVER USING A PUT OR POST
        // THEN READ BACK TO SEE IF YOUR CHANGE HAS HAPPENED
          Long me = new Long(17);
         
         Subscriber oldsub = cs.find(me);
         
         
         Subscriber updatesub = new Subscriber.Builder()
                 .Subscriber(oldsub)
                 .setUsername("newname")
                 .build();
         
         repo.save(updatesub);
         id = updatesub.getSubscriberID();

        HttpEntity<Subscriber> requestEntity = new HttpEntity<>(updatesub, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(URL + "api/Registration/update", HttpMethod.PUT, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }
    
     @Test(enabled = false)
    public void testreadClubById() {
        String cemeteryID = "2";
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery> responseEntity = restTemplate.exchange(URL + "api/Registration/id/" + cemeteryID, HttpMethod.GET, requestEntity, Cemetery.class);
        Cemetery cemetery = responseEntity.getBody();

        Assert.assertNotNull(cemetery);

    }

    @Test(enabled = false)
    public void testgetAllClubs() {
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery[]> responseEntity = restTemplate.exchange(URL + "api/Registration/show", HttpMethod.GET, requestEntity, Cemetery[].class);
        Cemetery[] cemeteries = responseEntity.getBody();
        for (Cemetery cemetery : cemeteries) {
            System.out.println("The Club Name is " + cemetery.getContactName());

        }

        Assert.assertTrue(cemeteries.length > 0);
    }
    private HttpEntity<?> getHttpEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return requestEntity;
    }

    private HttpHeaders getContentType() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;
    }

    
}