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
import com.cemeterylistingsweb.services.ViewListingByGraveNumberService;
import java.sql.Date;
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
 */
@Service
public class ViewListingByGraveNumberServiceImpl implements ViewListingByGraveNumberService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    @Autowired
    SubscriberRepository subRepo;
    
    @Override
    public List<PublishedDeceasedListing> findListingByGraveNumber(String number){
         List<PublishedDeceasedListing> lists=publishRepo.findAll();
        //find listing by dob
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : lists ){
            if(pubListing.getGraveNumber().equals(number))
                list.add(pubListing);
        }

        return list;
    }
    
    @Override
    public List<PublishedDeceasedListing> findListingByGraveNumber(String number, Long SubId){
        List<PublishedDeceasedListing> lists=publishRepo.findAll();
        Subscriber sub = subRepo.findOne(SubId);
        
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : lists ){
            
             SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = (Date) format.parse(pubListing.getDateOfDeath());
            } catch (ParseException ex) {
                Logger.getLogger(ViewListingBySubscriberServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date dod = new java.sql.Date(parsed.getTime());
            
            if(pubListing.getGraveNumber().equals(number) && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getValidUntil()) )
                list.add(pubListing);
        }

        return list;
    }
    
    @Override
    public PublishedDeceasedListing find(Long id) {
        return publishRepo.findOne(id);
    }

    @Override
    public PublishedDeceasedListing persist(PublishedDeceasedListing entity) {
        return publishRepo.save(entity);
    }

    @Override
    public PublishedDeceasedListing merge(PublishedDeceasedListing entity) {
        if(entity.getPublishedListingID()!=null){
            return publishRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(PublishedDeceasedListing entity) {
        publishRepo.delete(entity);
    }

    @Override
    public List<PublishedDeceasedListing> findAll() {
        return publishRepo.findAll();
    }
}
