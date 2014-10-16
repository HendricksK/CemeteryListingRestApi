/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.SubscriptionService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService{
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
    
   
    
    public void updateSubscriber(Long subId){
        Subscriber entity = SubscrRepo.findOne(subId);
        if(entity.getUsername()!=null){
            Subscriber update = new Subscriber.Builder()
                 .Subscriber(entity)
                 .build();
            SubscrRepo.save(update);
        }
        
    }
    public void deleteSubscriber(Long subId){
        SubscrRepo.delete(subId);
    }
}
