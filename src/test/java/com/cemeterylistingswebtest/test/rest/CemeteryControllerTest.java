/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingswebtest.test.rest;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.services.CemeteryListingService;
import static com.cemeterylistingswebtest.test.domain.CemeteryTest.repo;
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
 * @author Kurvin Hendricks
 */
public class CemeteryControllerTest {
    
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "http://localhost:8081/CemeteryListingsWebApp/";
    private CemeteryListingService cs;
    private Long id;
    
    @Test(enabled = false)
    public void testCreate() {
       System.out.println("Cemetery Testing");
         
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
        
        HttpEntity<Cemetery> requestEntity = new HttpEntity<>(newCemetery, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = 
        restTemplate.exchange(URL + "api/cemetery/create", HttpMethod.POST, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        id = newCemetery.getId();
    }
    
    @Test(enabled = false, dependsOnMethods = "testCreate")
    public void testClubUpdate() {
        // LEFT AS AN EXERCISE FOR YOU
        // GET THE CLUB and THEN CHANGE AND MAKE A COPY
        //THEN SEND TO THE SERVER USING A PUT OR POST
        // THEN READ BACK TO SEE IF YOUR CHANGE HAS HAPPENED
         Long me = new Long(17);
         Cemetery oldCemetery = cs.find(me);
         
         Cemetery updateCemetery = new Cemetery.Builder()
                 .Cemetery(oldCemetery)
                 .setContactNumber("0215554412")
                 .build();
         
         repo.save(updateCemetery);
         id = updateCemetery.getId();

        HttpEntity<Cemetery> requestEntity = new HttpEntity<>(updateCemetery, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(URL + "api/cemetery/update", HttpMethod.PUT, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test(enabled = false)
    public void testreadClubById() {
        String cemeteryID = "2";
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery> responseEntity = restTemplate.exchange(URL + "api/cemetery/id/" + cemeteryID, HttpMethod.GET, requestEntity, Cemetery.class);
        Cemetery cemetery = responseEntity.getBody();

        Assert.assertNotNull(cemetery);

    }

    @Test(enabled = false)
    public void testgetAllClubs() {
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery[]> responseEntity = restTemplate.exchange(URL + "api/cemetery/show", HttpMethod.GET, requestEntity, Cemetery[].class);
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
