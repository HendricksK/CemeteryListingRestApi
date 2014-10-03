/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.Subscriber;

/**
 *
 * @author Ryno
 */
public interface LoginService extends Services<Subscriber,Long>{
    public boolean authenticate(String uname, String pword);

    
}
