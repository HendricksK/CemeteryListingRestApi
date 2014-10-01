/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingswebtest.test.rest;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.services.CemeteryListingService;
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
    
    @Test
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
