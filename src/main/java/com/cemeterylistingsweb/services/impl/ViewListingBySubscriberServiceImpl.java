/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.ViewListingBySubscriberService;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */@Service
public class ViewListingBySubscriberServiceImpl implements ViewListingBySubscriberService{
    @Autowired
    SubscriberRepository SubscrRepo;
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    
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
    public List<PublishedDeceasedListing> findListingBySubscriber(java.sql.Date subDate, java.sql.Date validDate){
        List<PublishedDeceasedListing> publists=publishRepo.findAll();
        //find listing by dob
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : publists ){
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = format.parse(pubListing.getDateOfDeath());
            } catch (ParseException ex) {
                Logger.getLogger(ViewListingBySubscriberServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date dod = new java.sql.Date(parsed.getTime());
            
            //Date dod = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(pubListing.getDateOfDeath());
            System.out.println(dod);
            System.out.println(subDate);
            System.out.println(validDate);
            
            if(dod.after(subDate) && dod.before(validDate) )
                System.out.println(dod);
                System.out.println("added"
                        + "");
                list.add(pubListing);
        }

        return list;
    }
    
    @Override
     public List<PublishedDeceasedListing> findListingBySubscriber(Long subID){
        List<PublishedDeceasedListing> publists=publishRepo.findAll();
        //find listing by dob
        Subscriber sub = SubscrRepo.findOne(subID);
        
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : publists ){
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = format.parse(pubListing.getDateOfDeath());
            } catch (ParseException ex) {
                Logger.getLogger(ViewListingBySubscriberServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date dod = new java.sql.Date(parsed.getTime());
            
            //Date dod = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(pubListing.getDateOfDeath());
            System.out.println(dod);
            
            
            if(dod.after(sub.getSubscriptionDate()) && dod.before(sub.getValidUntil()) )
                System.out.println(dod);
                System.out.println("added"
                        + "");
                list.add(pubListing);
        }

        return list;
    }
}
