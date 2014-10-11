/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.services;

import com.cemeterylistingsweb.domain.Cemetery;
import com.cemeterylistingsweb.domain.PublishedDeceasedListing;
import java.util.List;

/**
 *
 * @author Ryno
 */
public interface ViewListingByCemetery extends Services<Cemetery,Long>{
    public List<PublishedDeceasedListing> findListingByCemetery(Long cemId);
    public List<PublishedDeceasedListing> findListingByCemetery(Long cemId, Long subId);
}
