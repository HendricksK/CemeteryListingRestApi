/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.services.AdminRegisterSubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
    @RequestMapping(value = "subscriberDetails", method = RequestMethod.GET)
    public String SubscriberDetails(){
        return "subscriberDetails";
    }
    
}
