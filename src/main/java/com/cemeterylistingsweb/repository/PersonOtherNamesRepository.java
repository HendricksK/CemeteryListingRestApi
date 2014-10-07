/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cemeterylistingsweb.domain.PersonOtherNames;

/**
 *
 * @author Kurvin Hendricks
 */
@Repository
public interface PersonOtherNamesRepository extends JpaRepository<PersonOtherNames, Long>{
    
}
