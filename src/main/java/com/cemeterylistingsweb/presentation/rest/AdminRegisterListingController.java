/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.PersonOtherNames;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.services.AdminUpdatePublishedListingService;
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
 * @author Michael
 */
@Controller
@RequestMapping(value = "api/AdminUpdateListing")
public class AdminRegisterListingController {
    @Autowired
    AdminUpdatePublishedListingService aupls;
    
    @RequestMapping(value = "update",method = RequestMethod.PUT) //This the uri e.g http://localhost:8084/askweb/api/club/update
    @ResponseBody
    public String update(@RequestBody PublishedDeceasedListing pdl) {
        aupls.updatePublishedlisting(pdl);
        System.out.println(" Update Called ");
        return "listing updated";
    }
    
    @RequestMapping(value = "update2",method = RequestMethod.PUT) //This the uri e.g http://localhost:8084/askweb/api/club/update
    @ResponseBody
    public String update2(@RequestBody PublishedDeceasedListing pdl) {
        
        aupls.merge(pdl);
        System.out.println(" Update Called ");
        return "Published Listing updated";
    }
}
