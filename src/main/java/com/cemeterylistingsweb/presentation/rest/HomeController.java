/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.presentation.rest;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.services.CemeteryListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kurvin Hendricks
 */
@Controller
public class HomeController {
    
    @Autowired
    CemeteryListingService cs;
    
    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public List<Cemetery> getAll(){
        return cs.findAll();
    }
}
