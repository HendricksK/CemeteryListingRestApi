/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;

/**
 *
 * @author Ryno
 */
public interface AdminRegisterListingService extends Services<RequiresApprovalDeceasedListing, Long>{
    public void registerDeceasedListing(String fn, String ln, String maiden, String gender, String dob, String dod, String inscription, String graveNum, String graveImage, String contactname, String contactNum, Long subID);
    public Long registerDeceasedListingReturn(String fn, String ln, String maiden, String gender, String dob, String dod, String inscription, String graveNum, String graveImage, String contactname, String contactNum, Long subID);
 
}
