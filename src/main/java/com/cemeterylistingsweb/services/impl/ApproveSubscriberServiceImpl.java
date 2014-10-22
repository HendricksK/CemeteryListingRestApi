/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.ApproveSubscriberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class ApproveSubscriberServiceImpl implements ApproveSubscriberService{
        @Autowired
    SubscriberRepository SubscrRepo;
        
        @Override
    public  boolean avalaibleUsername(String user){
        List<Subscriber> sublist = SubscrRepo.findAll();
        boolean available=true;
        for(Subscriber sub: sublist){
            if(sub.getUsername().equalsIgnoreCase(user))
                available=false;
        }
        if(available)
            return true;
        else
            return false;
    }

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
}
