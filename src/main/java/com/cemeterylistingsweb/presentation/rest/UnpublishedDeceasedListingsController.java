/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.services.ApproveDeceasedListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kurvin Hendricks
 */
@Controller
@RequestMapping(value = "api/unpublishedListings") 
public class UnpublishedDeceasedListingsController {
    @Autowired
    ApproveDeceasedListingService adls;
    
    @RequestMapping(value = "create",method = RequestMethod.POST) // This the uri e.g http://localhost:8084/askweb/api/club/create
    @ResponseBody //Converts output or response to JSON String
    public String create(@RequestBody RequiresApprovalDeceasedListing PDL) { // @RequestBody for converting incoming JSON call to Object
        adls.persist(PDL);
        System.out.println(" Create the Called ");
        return "createUnpublishedListing";
    }
    
    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public List<RequiresApprovalDeceasedListing> getAll(){
        return adls.findAll();
    }
    
    @RequestMapping(value = "update",method = RequestMethod.PUT) //This the uri e.g http://localhost:8084/askweb/api/club/update
    @ResponseBody
    public String update(@RequestBody RequiresApprovalDeceasedListing PDL) {
        adls.merge(PDL);
        System.out.println(" Update Called ");
        return "updated";
    }
    
    @RequestMapping(value = "id/{id}",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public RequiresApprovalDeceasedListing getClub(@PathVariable Long id) { //@PathVariable used to bind the id value
        
        System.out.println(" ID called ");
        return adls.find(id);
    }
    
    @RequestMapping(value = "delete/{id}",method = RequestMethod.POST) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public String deleteClub(@PathVariable Long id) { //@PathVariable used to bind the id value
        RequiresApprovalDeceasedListing deleteME = adls.find(id);
        adls.remove(deleteME);
        return "deleted";
    }
    
    @RequestMapping(value = "details", method = RequestMethod.GET)
    public String adminHome(){
        return "unpublishedDetailsPage";
    }
}
