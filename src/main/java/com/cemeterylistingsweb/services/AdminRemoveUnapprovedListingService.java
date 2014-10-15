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
public interface AdminRemoveUnapprovedListingService extends Services<RequiresApprovalDeceasedListing, Long>{
    public void removeRequiresApprovalListing(Long subId);
}
