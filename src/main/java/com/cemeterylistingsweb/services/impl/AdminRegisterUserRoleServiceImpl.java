/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services.impl;

import com.cemeterylistingsweb.domain.UserRole;
import com.cemeterylistingsweb.repository.UserRoleRepository;
import com.cemeterylistingsweb.services.AdminRegisterUserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public class AdminRegisterUserRoleServiceImpl implements AdminRegisterUserRoleService{
    @Autowired
    private UserRoleRepository repo; 
    
    @Override
    public UserRole find(Long id) {
        return repo.findOne(id);    }

    @Override
    public UserRole persist(UserRole entity) {
        return repo.save(entity);    }

    @Override
    public UserRole merge(UserRole entity) {
        return repo.save(entity);    }

    @Override
    public void remove(UserRole entity) {
        repo.delete(entity);    }

    @Override
    public List<UserRole> findAll() {
        return repo.findAll();    }
    
    @Override
     public void RegisterUserRole(int level){
          UserRole newRole = new UserRole.Builder()
                 .setLevel(level)
                 .build();
         
         repo.save(newRole);
     }
    @Override
     public Long RegisterUserRoleReturn(int level){
         Long id; 
         UserRole newRole = new UserRole.Builder()
                 .setLevel(level)
                 .build();
         
         repo.save(newRole);
         return id = newRole.getUserRoleID();
     }
}
