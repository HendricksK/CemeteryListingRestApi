/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation;

import com.cemeterylistingsweb.services.ViewListingByMaidenNameService;
import java.util.List;
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
@RequestMapping(value = "api/search") 
public class SearchController {
    
    @Autowired
    ViewListingByMaidenNameService searchName;
    
    @RequestMapping(value = "siteSearch", method = RequestMethod.GET)
    public String login(){
        return "searchDetailsPage";
    }
    
    @RequestMapping(value = "nameSearch",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    @ResponseBody
    public List test(@RequestParam String name) { 
        return searchName.findListingByMaidenName(name);
    }
    
    @RequestMapping(value = "contactus",method = RequestMethod.GET) //http://localhost:8084/askweb/api/club/1234
    public String contactus(@RequestParam String name) { 
        return "contact";
    }
}
