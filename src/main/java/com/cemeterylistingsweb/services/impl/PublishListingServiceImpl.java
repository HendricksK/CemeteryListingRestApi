/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.services.PublishListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class PublishListingServiceImpl implements PublishListingService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    @Autowired
    RequiresApprovalDeceasedListingRepository waitRepo;
    
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
    
    public void publishListings(RequiresApprovalDeceasedListing listing){
        PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName(listing.getFirstName())
                 .setSurname(listing.getSurname())
                 .setMaidenName(listing.getMaidenName())
                 .setGender(listing.getGender())
                 .setDateOfBirth(listing.getDateOfBirth())
                 .setDateOfDeath(listing.getDateOfDeath())
                 .setGraveInscription(listing.getGraveInscription())
                 .setGraveNumber(listing.getGraveNumber())
                 .setImageOfBurialSite(listing.getImageOfBurialSite())
                 .setLastKnownContactName(listing.getLastKnownContactName())
                 .setLastKnownContactNumber(listing.getLastKnownContactNumber())
                 .setSubscriberSubmitID(listing.getSubscriberSubmitID())
                 .build();
                
        publishRepo.save(newListing);
        
        //waitRepo.delete(listing);
        
    }
     public Long publishListingsReturn(RequiresApprovalDeceasedListing listing){
        long id;
         PublishedDeceasedListing newListing = new PublishedDeceasedListing.Builder()
                 .setFirstName(listing.getFirstName())
                 .setSurname(listing.getSurname())
                 .setMaidenName(listing.getMaidenName())
                 .setGender(listing.getGender())
                 .setDateOfBirth(listing.getDateOfBirth())
                 .setDateOfDeath(listing.getDateOfDeath())
                 .setGraveInscription(listing.getGraveInscription())
                 .setGraveNumber(listing.getGraveNumber())
                 .setImageOfBurialSite(listing.getImageOfBurialSite())
                 .setLastKnownContactName(listing.getLastKnownContactName())
                 .setLastKnownContactNumber(listing.getLastKnownContactNumber())
                 .setSubscriberSubmitID(listing.getSubscriberSubmitID())
                 .build();
                
        publishRepo.save(newListing);
        
        //waitRepo.delete(listing);
        return id=newListing.getPublishedListingID();
    }
}
