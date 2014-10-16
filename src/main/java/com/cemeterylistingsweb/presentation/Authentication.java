/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Kurvin Hendricks
 */
@Controller
@RequestMapping(value = "api/auth") 
public class Authentication {
    
    
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
}
