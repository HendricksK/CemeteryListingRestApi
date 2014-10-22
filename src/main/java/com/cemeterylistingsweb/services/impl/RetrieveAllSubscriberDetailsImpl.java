/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.RetrieveAllSubscriberDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class RetrieveAllSubscriberDetailsImpl implements RetrieveAllSubscriberDetails{
    @Autowired
    SubscriberRepository SubscrRepo;
    
    @Override
    public Subscriber find(Long id) {
        return SubscrRepo.findOne(id);
    }

    @Override
    public Subscriber persist(Subscriber entity) {
        return SubscrRepo.save(entity);
    }

    @Override
    public Subscriber merge(Subscriber entity) {
        if(entity.getUsername()!=null){
            return SubscrRepo.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Subscriber entity) {
        SubscrRepo.delete(entity);
    }

    @Override
    public List<Subscriber> findAll() {
        return SubscrRepo.findAll();
    }
    
    @Override
    public Subscriber getSubscriberByUsername(String username){
        List<Subscriber> sublist = SubscrRepo.findAll();
        Subscriber subscr = null;
        for(Subscriber sub:sublist){
            if(sub.getUsername().equalsIgnoreCase(username))
                return subscr=sub;
        }
        return subscr;
    }
}
