/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import com.cemeterylistingsweb.domain.RequiresApprovalDeceasedListing;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ryno
 */

public interface PublishListingService extends Services<PublishedDeceasedListing, Long>{
    public void publishListings(RequiresApprovalDeceasedListing listing);
    public Long publishListingsReturn(RequiresApprovalDeceasedListing listing);
}
