/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.services.ApproveSubscriberService;
import com.cemeterylistingsweb.services.RetrieveAllSubscriberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kurvin Hendricks
 */
@Controller
@RequestMapping(value = "api/auth") 
public class Authentication {
    
    @Autowired
    RetrieveAllSubscriberDetails getUser;
    
    @Autowired
    ApproveSubscriberService checkUserName;
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminLogin(){
        return "adminLogin";
    }
    
    @RequestMapping(value = "loggedIn", method = RequestMethod.GET)
    public String adminHome(){
        return "adminHome";
    }
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String newSubscriber(){
        return "createSubscriber";
    }
    
    @RequestMapping(value = "getUserDetails",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public Subscriber getUserDetails(@RequestParam String username) { 
        return getUser.getSubscriberByUsername(username);
    }
    
    @RequestMapping(value = "checkUserName",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    public Boolean checkUserNameAvail(@RequestParam String username) { 
        return checkUserName.avalaibleUsername(username);
    }
}
