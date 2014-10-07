/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.LoginService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    SubscriberRepository SubscrRepo;
    
    @Override
    public Subscriber find(Long id) {
        return SubscrRepo.findOne(id);
    }

    @Override
    public Subscriber persist(Subscriber entity) {
        return SubscrRepo.save(entity);
    }

    @Override
    public Subscriber merge(Subscriber entity) {
        if(entity.getUsername()!=null){
            return SubscrRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Subscriber entity) {
        SubscrRepo.delete(entity);
    }

    @Override
    public List<Subscriber> findAll() {
        return SubscrRepo.findAll();
    }

    @Override
    public boolean authenticate(String uname, String pword) {
        List<Subscriber> subscrList = SubscrRepo.findAll();
        boolean login = false;
        int error=0;
       
        if(uname.isEmpty() && pword.isEmpty())
            error=1;
        else if(uname.equals("") && pword.equals(""))
            error=2;
        else if(uname.length()>0 && pword.isEmpty() || pword.equals(""))
            error=3;
        else if(pword.length()>0 && uname.isEmpty() || pword.equals(""))
            error=4;
        if(error > 0)
            return false;
        
        for(Subscriber listing : subscrList){
            if(listing.getUsername().equalsIgnoreCase(uname) && listing.getPwd().equalsIgnoreCase(pword)){
                //add to list
                //subList.add(listing);
                login = true;
            }
        }
        
        if(login)
            return true;
        else
            return false;
        
    }
    
    
}
