/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingswebtest.test.rest;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.PersonOtherNames;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.services.PublishListingService;
import static com.cemeterylistingswebtest.test.domain.PublishedDeceasedListingTest.repoList;
import java.util.Collections;
import java.util.List;
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
public class DisplayDeceasedControllerTest {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "http://localhost:8081/CemeteryListingsWebApp/";
    private PublishListingService cs;
    private Long id;
    
    @Test(enabled = true)
    public void testCreate() {
       System.out.println("PublishListingService Testing");
         
         Long subID = new Long(17);
        List<PersonOtherNames> names = null;
        
        PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName("Hendrika")
                 .setSurname("Fourie")
                 .setMaidenName("Gerber")
                 .setGender("Female")
                 .setDateOfBirth("08/06/1969")
                 .setDateOfDeath("14/02/2005")
                 .setGraveInscription("Hippiest person eva")
                 .setGraveNumber("2456")
                 .setImageOfBurialSite("/images/001.jpg")
                 .setLastKnownContactName("Berry")
                 .setLastKnownContactNumber("0725576482")
                 .setSubscriberSubmitID(subID)
                 .setNames(names)
                 .build();
        
        HttpEntity<PublishedDeceasedListing> requestEntity = new HttpEntity<>(newListing, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = 
        restTemplate.exchange(URL + "api/DeceasedListing/create", HttpMethod.POST, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        id = newListing.getPublishedListingID();
    }
    
    @Test(enabled = false, dependsOnMethods = "testCreate")
    public void testClubUpdate() {
        // LEFT AS AN EXERCISE FOR YOU
        // GET THE CLUB and THEN CHANGE AND MAKE A COPY
        //THEN SEND TO THE SERVER USING A PUT OR POST
        // THEN READ BACK TO SEE IF YOUR CHANGE HAS HAPPENED
        Long me = new Long(17);
         PublishedDeceasedListing oldpdl = cs.find(me);
         
         PublishedDeceasedListing updatepdl = new PublishedDeceasedListing.Builder()
                 .PublishedDeceasedListing(oldpdl)
                 .setGender("male")
                 .build();
         
         repoList.save(updatepdl);
         id = updatepdl.getPublishedListingID();

        HttpEntity<PublishedDeceasedListing> requestEntity = new HttpEntity<>(updatepdl, getContentType());
//        Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(URL + "api/DeceasedListing/update", HttpMethod.PUT, requestEntity, String.class);
        System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
        System.out.println(" THE RESPONSE STATUS CODE " + responseEntity.getStatusCode());
        System.out.println(" THE RESPONSE IS HEADERS " + responseEntity.getHeaders());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test(enabled = false)
    public void testreadClubById() {
        String cemeteryID = "2";
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery> responseEntity = restTemplate.exchange(URL + "api/DeceasedListing/id/" + cemeteryID, HttpMethod.GET, requestEntity, Cemetery.class);
        Cemetery cemetery = responseEntity.getBody();

        Assert.assertNotNull(cemetery);

    }

    @Test(enabled = false)
    public void testgetAllClubs() {
        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<Cemetery[]> responseEntity = restTemplate.exchange(URL + "api/DeceasedListing/show", HttpMethod.GET, requestEntity, Cemetery[].class);
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
