/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.services.AdminRegisterSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Michael
 */
@Controller
@RequestMapping(value = "api/subscriber") 
public class SubscriberController {
    
    @Autowired
    AdminRegisterSubscriberService arss;
    
    
    @RequestMapping(value = "createSubscriber", method = RequestMethod.GET)
    public String createSubscriber(){
        return "createSubscriber";
    }
    
    @RequestMapping(value = "create",method = RequestMethod.POST) // This the uri e.g http://localhost:8084/askweb/api/club/create
    @ResponseBody //Converts output or response to JSON String
    public String create(@RequestBody Subscriber subscriber) { // @RequestBody for converting incoming JSON call to Object
        
        arss.persist(subscriber);
        System.out.println(" Create Subscriber Called ");
        return "Subscriber created";
    }
    
    
    @RequestMapping(value = "subscriberDetails", method = RequestMethod.GET)
    public String SubscriberDetails(){
        return "subscriberDetails";
    }
    
}
