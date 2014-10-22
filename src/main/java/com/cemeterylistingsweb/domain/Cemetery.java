/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ME
 */
@Entity
public class Cemetery implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Embedded
    Location location;
    String contactName;
    String contactNumber;

    public Cemetery() {
    }

    private Cemetery(Builder aThis) {
       this.contactName = aThis.contactName;
       this.contactNumber = aThis.contactNumber;
       this.id = aThis.id;
       this.location = aThis.location;
    }
    
    private Cemetery(Cemetery aThis) {
       this.contactName = aThis.contactName;
       this.contactNumber = aThis.contactNumber;
       this.id = aThis.id;
       this.location = aThis.location;
    }

    public Long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    
    public static class Builder{
        Long id;
        Location location;
        String contactName;
        String contactNumber;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setLocation(Location location) {
            this.location = location;
            return this;
        }

        public Builder setContactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }
        
        public Cemetery build(){
            return new Cemetery(this);
        }
        
       public Builder Cemetery(Cemetery aThis) {
        this.contactName = aThis.contactName;
        this.contactNumber = aThis.contactNumber;
        this.id = aThis.id;
        this.location = aThis.location;
        return this;
    }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cemetery other = (Cemetery) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
