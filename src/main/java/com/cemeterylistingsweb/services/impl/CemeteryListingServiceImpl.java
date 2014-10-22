/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.CemeteryListingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class CemeteryListingServiceImpl implements CemeteryListingService{
    
    @Autowired
    CemeteryRepository cemRepo;
    @Autowired
    PublishedDeceasedListingRepository deadRepo;
    @Autowired
    SubscriberRepository subRepo;
    
    @Override
    public List<Cemetery> findAllCemeteries() {
        List<Cemetery> cemList = cemRepo.findAll();
        return cemList;
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
