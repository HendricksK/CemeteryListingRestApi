/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.domain.Subscriber;
import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.CemeteryRepository;
import com.cemeterylistingsweb.repository.SubscriberRepository;
import com.cemeterylistingsweb.services.CemeteryRegistrationService;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class RegistrationServiceImpl implements CemeteryRegistrationService{
    @Autowired
    SubscriberRepository SubscrRepo;
    @Autowired
    CemeteryRepository cemRepo;
    
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
     public Long registerCemeteryReturn(Location local, String contactName, String contactNumber){
         Long id; 
         Cemetery newCemetery = new Cemetery.Builder()
                 .setContactName(contactName)
                 .setContactNumber(contactNumber)
                 .setLocation(local)
                 .build();
     
        cemRepo.save(newCemetery);
        
        return id = newCemetery.getId();
         
     }
    
    public void registerCemetery(Location local, String contactName, String contactNumber){
        /*Location local = new Location.Builder()
                 .setCemeteryName("Palm Springs")
                 .setCountry("America")
                 .setDistrict_state("Washington")
                 .setLocationOfCemetery("12.06.12:45.63.89")
                 .setProvince_State("New Jersey")
                 .setTown("Marlboro")
                 .build();*/
        
        //Location locals = registerLocation(); 
        
         Cemetery newCemetery = new Cemetery.Builder()
                 .setContactName(contactName)
                 .setContactNumber(contactNumber)
                 .setLocation(local)
                 .build();
     
        cemRepo.save(newCemetery);
    }
    
    public Location registerLocation(String cemName, String Country, String state, String gps, String prov, String town){
        Location local = new Location.Builder()
                 .setCemeteryName(cemName)
                 .setCountry(Country)
                 .setDistrict_state(state)
                 .setLocationOfCemetery(gps)
                 .setProvince_State(prov)
                 .setTown(town)
                 .build();
        return local;
    }
    
    public void registerCemeteryLocation(String cemName, String Country, String state, String gps, String prov, String town,String contactName, String contactNumber ){
         Location local = new Location.Builder()
                 .setCemeteryName(cemName)
                 .setCountry(Country)
                 .setDistrict_state(state)
                 .setLocationOfCemetery(gps)
                 .setProvince_State(prov)
                 .setTown(town)
                 .build();
        
        //Location locals = registerLocation(); 
        
         Cemetery newCemetery = new Cemetery.Builder()
                 .setContactName(contactName)
                 .setContactNumber(contactNumber)
                 .setLocation(local)
                 .build();
     
        cemRepo.save(newCemetery);
        
    }
    
}
