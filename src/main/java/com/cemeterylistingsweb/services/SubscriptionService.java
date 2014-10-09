/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.Subscriber;
import java.sql.Date;

/**
 *
 * @author Ryno
 */
public interface SubscriptionService extends Services<Subscriber, Long>{
    public void registerSubscriber(String email, String first, String surname, String uname, String pword, Date sqlDate, int roleLevel);
    public void updateSubscriber(Long subId);
    public void deleteSubscriber(Long subId);
}
