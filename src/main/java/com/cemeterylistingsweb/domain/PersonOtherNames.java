/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Kurvin Hendricks
 */
@Entity
public class PersonOtherNames implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String names;
    private Long publishedListingID;

    public PersonOtherNames() {
    }

    private PersonOtherNames(Builder aThis) {
        this.id = aThis.id;
        this.names = aThis.names;
        this.publishedListingID = aThis.publishedListingID;
    }
    
    private PersonOtherNames(PersonOtherNames aThis) {
        this.id = aThis.id;
        this.names = aThis.names;
        this.publishedListingID = aThis.publishedListingID;
    }
    
    public Long getId() {
        return id;
    }

    public String getNames() {
        return names;
    }
    
    public Long getPublishedListingID(){
        return publishedListingID;
    }
    
    public static class Builder{
        private Long id;
        private String names;
        private Long publishedListingID;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setNames(String names) {
            this.names = names;
            return this;
        }

        public Builder setPublishedListingID(Long publishedListingID) {
            this.publishedListingID = publishedListingID;
            return this;
        }
        
        public PersonOtherNames build(){
            return new PersonOtherNames(this);
        }
        
    public Builder PersonOtherNames(PersonOtherNames aThis) {
        this.id = aThis.id;
        this.names = aThis.names;
        this.publishedListingID = aThis.publishedListingID;
        return this;
    }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final PersonOtherNames other = (PersonOtherNames) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
