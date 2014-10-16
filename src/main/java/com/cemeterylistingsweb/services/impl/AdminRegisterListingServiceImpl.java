/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.services.AdminRegisterListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class AdminRegisterListingServiceImpl implements AdminRegisterListingService{
    @Autowired
    RequiresApprovalDeceasedListingRepository deadRepo;
    @Autowired
    CemeteryRepository cemRepo;
    
    @Override
    public RequiresApprovalDeceasedListing find(Long id) {
        return deadRepo.findOne(id);
    }

    @Override
    public RequiresApprovalDeceasedListing persist(RequiresApprovalDeceasedListing entity) {
        return deadRepo.save(entity);
    }

    @Override
    public RequiresApprovalDeceasedListing merge(RequiresApprovalDeceasedListing entity) {
        if(entity.getRequiresApprovalDeceasedListingID()!=null){
            return deadRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(RequiresApprovalDeceasedListing entity) {
        deadRepo.delete(entity);
    }

    @Override
    public List<RequiresApprovalDeceasedListing> findAll() {
        return deadRepo.findAll();
    }
    
    @Override
    public void registerDeceasedListing(String fn, String ln, String maiden, String gender, String dob, String dod, String inscription, String graveNum, String graveImage, String contactname, String contactNum, Long subID){
        RequiresApprovalDeceasedListing newListing = new RequiresApprovalDeceasedListing.Builder()
                 .setFirstName(fn)
                 .setSurname(ln)
                 .setMaidenName(maiden)
                 .setGender(gender)
                 .setDateOfBirth(dob)
                 .setDateOfDeath(dod)
                 .setGraveInscription(inscription)
                 .setGraveNumber(graveNum)
                 .setImageOfBurialSite(graveImage)
                 .setLastKnownContactName(contactname)
                 .setLastKnownContactNumber(contactNum)
                 .setSubscriberSubmitID(subID)
                 //cemetery id
                 
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
    }
    
     @Override
    public Long registerDeceasedListingReturn(String fn, String ln, String maiden, String gender, String dob, String dod, String inscription, String graveNum, String graveImage, String contactname, String contactNum, Long subID){
        Long id;
        RequiresApprovalDeceasedListing newListing = new RequiresApprovalDeceasedListing.Builder()
                 .setFirstName(fn)
                 .setSurname(ln)
                 .setMaidenName(maiden)
                 .setGender(gender)
                 .setDateOfBirth(dob)
                 .setDateOfDeath(dod)
                 .setGraveInscription(inscription)
                 .setGraveNumber(graveNum)
                 .setImageOfBurialSite(graveImage)
                 .setLastKnownContactName(contactname)
                 .setLastKnownContactNumber(contactNum)
                 .setSubscriberSubmitID(subID)
                 //cemetery id
                 
                 //names
                 
                 .build();
         
         deadRepo.save(newListing);
         return id = newListing.getRequiresApprovalDeceasedListingID();
    }
}
