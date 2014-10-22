/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.ViewListingByCemetery;
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
public class ViewListingByCemeteryImpl implements ViewListingByCemetery{
     @Autowired
    CemeteryRepository cemRepo;
    @Autowired
    PublishedDeceasedListingRepository deadRepo;
    @Autowired
    SubscriberRepository subRepo;
    
    @Override
    public List<PublishedDeceasedListing> findListingByCemetery(Long cemId) {
        //
        List<PublishedDeceasedListing> deceasedList = deadRepo.findAll();
        List<PublishedDeceasedListing> Listings = new ArrayList();
        for(PublishedDeceasedListing listing : deceasedList){
            if(cemId.equals(listing.getCemeteryID())){//listing.getCemeteryID().equals(cemId)){
                //add to list
                Listings.add(listing);
            }
        }
        return Listings;
    }
    
    @Override
    public List<PublishedDeceasedListing> findListingByCemetery(Long cemId, Long subId) {
        //
        List<PublishedDeceasedListing> deceasedList = deadRepo.findAll();
        List<PublishedDeceasedListing> Listings = new ArrayList();
        Subscriber sub = subRepo.findOne(subId);
        
        for(PublishedDeceasedListing listing : deceasedList){
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = (Date) format.parse(listing.getDateOfDeath());
            } catch (ParseException ex) {
                Logger.getLogger(ViewListingBySubscriberServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date dod = new java.sql.Date(parsed.getTime());
            
            if(listing.getCemeteryID().equals(cemId) && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getLastContributionYear())){
                //add to list
                Listings.add(listing);
            }
        }
        return Listings;
    }

    @Override
    public Cemetery find(Long id) {
        return cemRepo.findOne(id);
    }

    @Override
    public Cemetery persist(Cemetery entity) {
        return cemRepo.save(entity);
    }

    @Override
    public Cemetery merge(Cemetery entity) {
        if(entity.getId()!=null){
            return cemRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Cemetery entity) {
        cemRepo.delete(entity);
    }

    @Override
    public List<Cemetery> findAll() {
        return cemRepo.findAll();
    }
    
    
}
