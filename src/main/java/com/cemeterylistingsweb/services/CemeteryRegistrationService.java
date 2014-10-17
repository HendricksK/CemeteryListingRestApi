/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.Location;
import com.cemeterylistingsweb.domain.Subscriber;

/**
 *
 * @author Ryno
 */
public interface CemeteryRegistrationService extends Services<Subscriber,Long>{
    public Long registerCemeteryReturn(Location local, String contactName, String contactNumber);
    public void registerCemetery(Location local, String contactName, String contactNumber);
    public Location registerLocation(String cemName, String Country, String state, String gps, String prov, String town);
    public void registerCemeteryLocation(String cemName, String Country, String state, String gps, String prov, String town,String contactName, String contactNumber );
}
