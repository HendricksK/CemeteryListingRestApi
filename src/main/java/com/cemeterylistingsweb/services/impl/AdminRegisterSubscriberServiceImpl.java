/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.AdminRegisterSubscriberService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class AdminRegisterSubscriberServiceImpl implements AdminRegisterSubscriberService{
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
    
    public void registerSubscriber(String email, String first, String surname, String uname, String pword, Date sqlDate,int roleLevel  ){
        /*Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2008);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 4);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         */
                 
         UserRole user = new UserRole.Builder()
                 .setLevel(roleLevel)
                 .build();
         
         //userRepo.save(user);
         //userRoleID = user.getUserRoleID();
         
         Subscriber newSub = new Subscriber.Builder()
                .setEmail(email)
                .setFirstName(first)
                .setSurname(surname)
                .setPwd(pword)
                .setUsername(uname)
                .setSubscriptionDate(sqlDate)
                .setUserRoleID(user)
                .build();
            
         
         SubscrRepo.save(newSub);
    }
    
    public Long registerSubscriberReturn(String email, String first, String surname, String uname, String pword, Date sqlDate,int roleLevel  ){
        /*Calendar calendar = Calendar.getInstance();
          calendar.set(Calendar.YEAR, 2008);
          calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
          calendar.set(Calendar.DATE, 4);
        
          java.sql.Date javaSqlDate = new java.sql.Date(calendar.getTime().getTime());
         */
                 Long id;
         UserRole user = new UserRole.Builder()
                 .setLevel(roleLevel)
                 .build();
         
         //userRepo.save(user);
         //userRoleID = user.getUserRoleID();
         
         Subscriber newSub = new Subscriber.Builder()
                .setEmail(email)
                .setFirstName(first)
                .setSurname(surname)
                .setPwd(pword)
                .setUsername(uname)
                .setSubscriptionDate(sqlDate)
                .setUserRoleID(user)
                .build();
            
         
         SubscrRepo.save(newSub);
         id = newSub.getSubscriberID();
         return id;
    }
}
