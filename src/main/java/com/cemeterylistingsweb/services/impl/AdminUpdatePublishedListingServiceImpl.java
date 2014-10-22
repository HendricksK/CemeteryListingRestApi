/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PersonOtherNames;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.services.AdminUpdatePublishedListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class AdminUpdatePublishedListingServiceImpl implements AdminUpdatePublishedListingService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    
    
    
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
    
    @Override
    public void updatePublishedlisting(Long pubId, String fname, String lname, String maidenName, String gender, String dob, String dod, String gInscrip, String gNum, String image, String lastContact, String lastContactNum, Long cemID, List<PersonOtherNames> names){
     PublishedDeceasedListing oldListing = publishRepo.findOne(pubId);
         
         PublishedDeceasedListing updateListing = new PublishedDeceasedListing.Builder()
                 .PublishedDeceasedListing(oldListing)
                 .setFirstName(fname)
                 .setSurname(lname)
                 .setMaidenName(maidenName)
                 .setGender(gender)
                 .setDateOfBirth(dob)
                 .setDateOfDeath(dod)
                 .setGraveInscription(gInscrip)
                 .setGraveNumber(gNum)
                 .setLastKnownContactName(lastContact)
                 .setLastKnownContactNumber(lastContactNum)
                 .setCemeteryID(cemID)
                 .setNames(names)
                 .build();
         
         publishRepo.save(updateListing);   
        
    }
    
    @Override
    public Long updatePublishedlistingReturn(Long pubId, String fname, String lname, String maidenName, String gender, String dob, String dod, String gInscrip, String gNum, String image, String lastContact, String lastContactNum, Long cemID, List<PersonOtherNames> names){
     PublishedDeceasedListing oldListing = publishRepo.findOne(pubId);
         Long id;
         PublishedDeceasedListing updateListing = new PublishedDeceasedListing.Builder()
                 .PublishedDeceasedListing(oldListing)
                 .setFirstName(fname)
                 .setSurname(lname)
                 .setMaidenName(maidenName)
                 .setGender(gender)
                 .setDateOfBirth(dob)
                 .setDateOfDeath(dod)
                 .setGraveInscription(gInscrip)
                 .setGraveNumber(gNum)
                 .setLastKnownContactName(lastContact)
                 .setLastKnownContactNumber(lastContactNum)
                 .setCemeteryID(cemID)
                 .setNames(names)
                 .build();
         
         publishRepo.save(updateListing);   
        return id = updateListing.getPublishedListingID();
    }
}
