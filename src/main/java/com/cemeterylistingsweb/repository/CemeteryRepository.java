/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.repository;

import com.cemeterylistingsweb.domain.Cemetery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kurvin Hendricks
 */

@Repository
public interface CemeteryRepository extends JpaRepository<Cemetery, Long>{
    
}
