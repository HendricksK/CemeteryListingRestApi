/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.UserRole;

/**
 *
 * @author Ryno
 */
public interface AdminRegisterUserRoleService extends Services<UserRole, Long>{
    public void RegisterUserRole(int level);
}
