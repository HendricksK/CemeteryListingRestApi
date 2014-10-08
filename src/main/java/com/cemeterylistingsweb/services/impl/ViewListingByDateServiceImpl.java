/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.ViewListingByDateService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class ViewListingByDateServiceImpl implements ViewListingByDateService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    @Autowired
    SubscriberRepository subRepo;
    
    
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
    public List<PublishedDeceasedListing> findListingByDOB(String dob){
        
        List<PublishedDeceasedListing> lists=publishRepo.findAll();
        //find listing by dob
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : lists ){
            if(pubListing.getDateOfBirth().equals(dob))
                list.add(pubListing);
        }
        
            return list;
    }
    public List<PublishedDeceasedListing> findListingByDOD(String dod){
        List<PublishedDeceasedListing> lists=publishRepo.findAll();
        //find listing by dob
        List<PublishedDeceasedListing> list = new ArrayList();
        for(PublishedDeceasedListing pubListing : lists ){
            if(pubListing.getDateOfDeath().equals(dod))
                list.add(pubListing);
        }

        return list;
    }
    
}
