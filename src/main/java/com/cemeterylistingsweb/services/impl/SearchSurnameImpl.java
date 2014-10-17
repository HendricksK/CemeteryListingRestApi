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
import com.cemeterylistingsweb.services.SearchSurname;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SearchSurnameImpl implements SearchSurname {
    
    @Autowired
    private PublishedDeceasedListingRepository repo;
    @Autowired
    private SubscriberRepository subRepo;
    
    @Override
     public List<PublishedDeceasedListing> getAllSurname(String surname){
         
          List<PublishedDeceasedListing> names = new ArrayList();
       
         List<PublishedDeceasedListing> all=new ArrayList();
        all = repo.findAll();
        
        if(surname.isEmpty() || surname.equals(""))
                return all;
       
        for (PublishedDeceasedListing all1 : all) {
            
            if (surname.equalsIgnoreCase(all1.getSurname())) {
                names.add(all1);
            }
            /*else if(surname.startsWith(all1.getSurname()))
                names.add(all1);
            else if(surname.contains(all1.getSurname()))
                names.add(all1);*/
        }
            
               return names;
        }
          
    @Override
    public List<PublishedDeceasedListing> getAllSurname(String surname, Long subId){
         
        List<PublishedDeceasedListing> names = new ArrayList();
        List<PublishedDeceasedListing> all = repo.findAll();
        Subscriber sub = subRepo.findOne(subId);
        
        //if(surname.isEmpty() || surname.equals("") )
          //      return all;
       
        for (PublishedDeceasedListing all1 : all) {
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = (Date) format.parse(all1.getDateOfDeath());
            } catch (ParseException ex) {
                Logger.getLogger(ViewListingBySubscriberServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date dod = new java.sql.Date(parsed.getTime());
            
            if(surname.isEmpty() || surname.equals("") && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getLastContributionYear()) )
                names.add(all1);
            else if (all1.getSurname().equals(surname) && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getLastContributionYear())) {
                names.add(all1);
            }
            else if(all1.getSurname().startsWith(surname) && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getLastContributionYear()))
                names.add(all1);
            else if(all1.getSurname().contains(surname) && dod.after(sub.getSubscriptionDate()) && dod.before(sub.getLastContributionYear()))
                names.add(all1);
        }
            
        return names;
    }
     
     
    
    @Override
    public PublishedDeceasedListing find(Long id) {
        return repo.findOne(id);

    }

    @Override
    public PublishedDeceasedListing persist(PublishedDeceasedListing entity) {
        return repo.save(entity);

    }

    @Override
    public PublishedDeceasedListing merge(PublishedDeceasedListing entity) {
        return repo.save(entity);

    }

    @Override
    public void remove(PublishedDeceasedListing entity) {
        repo.delete(entity);

    }

    @Override
    public List<PublishedDeceasedListing> findAll() {
        return repo.findAll();

    }

    
}
