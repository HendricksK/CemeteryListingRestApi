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
import com.cemeterylistingsweb.services.AdminRemoveUnapprovedListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class AdminRemoveUnapprovedListingServiceImpl implements AdminRemoveUnapprovedListingService{
    @Autowired
    PublishedDeceasedListingRepository publishRepo;
    @Autowired
    RequiresApprovalDeceasedListingRepository waitRepo;
    
    @Override
    public RequiresApprovalDeceasedListing find(Long id) {
        return waitRepo.findOne(id);
    }

    @Override
    public RequiresApprovalDeceasedListing persist(RequiresApprovalDeceasedListing entity) {
        return waitRepo.save(entity);
    }

    @Override
    public RequiresApprovalDeceasedListing merge(RequiresApprovalDeceasedListing entity) {
        if(entity.getRequiresApprovalDeceasedListingID()!=null){
            return waitRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(RequiresApprovalDeceasedListing entity) {
        waitRepo.delete(entity);
    }

    @Override
    public List<RequiresApprovalDeceasedListing> findAll() {
        return waitRepo.findAll();
    }
    
    public void removeRequiresApprovalListing(Long subId){
        
        waitRepo.delete(waitRepo.findOne(subId));
    }
}
