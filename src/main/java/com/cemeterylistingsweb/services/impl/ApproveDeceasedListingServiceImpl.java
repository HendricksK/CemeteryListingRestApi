/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.RequiresApprovalDeceasedListingRepository;
import com.cemeterylistingsweb.services.ApproveDeceasedListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class ApproveDeceasedListingServiceImpl implements ApproveDeceasedListingService{
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
    public boolean approveDeceasedListing(Long id){
        
        return false;
    }
}
