/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.services.ViewListingByCemetery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Michael
 */
@Controller
@RequestMapping(value = "api/cemeteryPublishedListings") 
public class CemeteryPublishedListingController {
    @Autowired
    ViewListingByCemetery vbc;
    
    @RequestMapping(value = "getListingsByCemeteryID/{id}",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public List<PublishedDeceasedListing> getListingsByCemeteryID(@RequestParam Long id) { //@PathVariable used to bind the id value
        System.out.println(" ID called ");
        return vbc.findListingByCemetery(id);
    }
}
