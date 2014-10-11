/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.repository.PublishedDeceasedListingRepository;
import com.cemeterylistingsweb.services.ViewListingByLocationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zaakir
 */
@Service
public class ViewListingByLocationServiceImpl implements ViewListingByLocationService{
    
    @Autowired
    private PublishedDeceasedListingRepository repo;

    @Override
    public List<PublishedDeceasedListing> getAllDeceased(long location) {
        
       List<PublishedDeceasedListing> deceased = new ArrayList();
        List<PublishedDeceasedListing> all = repo.findAll();
        
       
        for (PublishedDeceasedListing all1 : all) {
            if (all1.getCemeteryID().equals(location)) {
                deceased.add(all1);
            }
        }
            
               return deceased;
        }

    @Override
    public PublishedDeceasedListing find(Long id) {
        return repo.findOne(id);    }

    @Override
    public PublishedDeceasedListing persist(PublishedDeceasedListing entity) {
        return repo.save(entity);    }

    @Override
    public PublishedDeceasedListing merge(PublishedDeceasedListing entity) {
        return repo.save(entity);    }

    @Override
    public void remove(PublishedDeceasedListing entity) {
        repo.delete(entity);    }

    @Override
    public List<PublishedDeceasedListing> findAll() {
        return repo.findAll();    }
    
}
