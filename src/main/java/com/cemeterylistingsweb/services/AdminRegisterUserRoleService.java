/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.UserRole;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */
@Service
public interface AdminRegisterUserRoleService extends Services<UserRole, Long>{
    public void RegisterUserRole(int level);
    public Long RegisterUserRoleReturn(int level);
}
