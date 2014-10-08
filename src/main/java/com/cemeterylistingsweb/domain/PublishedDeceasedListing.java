/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ME
 */
@Entity
public class PublishedDeceasedListing implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id // deaulft 
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long PublishedListingID;
    String firstName;
    String surname;
    String maidenName;
    String gender;
    String dateOfBirth;
    String dateOfDeath;
    String graveInscription;
    String graveNumber;
    String imageOfBurialSite; // will need to be done in binary or saved in a online directory 
    String lastKnownContactName;
    String lastKnownContactNumber;
    Long cemeteryID;
    Long subscriberApprovedID;
    Long subscriberSubmitID;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="Other_names")
    List<PersonOtherNames> names;
    
    public PublishedDeceasedListing(){}

    private PublishedDeceasedListing(Builder aThis) {
       this.PublishedListingID = aThis.PublishedListingID;
       this.cemeteryID = aThis.cemeteryID;
       this.dateOfBirth = aThis.dateOfBirth;
       this.dateOfDeath = aThis.dateOfDeath;
       this.firstName = aThis.firstName;
       this.gender = aThis.gender;
       this.graveInscription = aThis.graveInscription;
       this.graveNumber = aThis.graveNumber;
       this.imageOfBurialSite = aThis.imageOfBurialSite;
       this.lastKnownContactName = aThis.lastKnownContactName;
       this.lastKnownContactNumber = aThis.lastKnownContactNumber;
       this.surname = aThis.surname;
       this.maidenName = aThis.maidenName;
       this.subscriberApprovedID = aThis.subscriberApprovedID;
       this.subscriberSubmitID = aThis.subscriberSubmitID;
       this.names = aThis.names;
    }
    
     private PublishedDeceasedListing(PublishedDeceasedListing aThis) {
       this.PublishedListingID = aThis.PublishedListingID;
       this.cemeteryID = aThis.cemeteryID;
       this.dateOfBirth = aThis.dateOfBirth;
       this.dateOfDeath = aThis.dateOfDeath;
       this.firstName = aThis.firstName;
       this.gender = aThis.gender;
       this.graveInscription = aThis.graveInscription;
       this.graveNumber = aThis.graveNumber;
       this.imageOfBurialSite = aThis.imageOfBurialSite;
       this.lastKnownContactName = aThis.lastKnownContactName;
       this.lastKnownContactNumber = aThis.lastKnownContactNumber;
       this.surname = aThis.surname;
       this.maidenName = aThis.maidenName;
       this.subscriberApprovedID = aThis.subscriberApprovedID;
       this.subscriberSubmitID = aThis.subscriberSubmitID;
       this.names = aThis.names;
    } 

    public Long getPublishedListingID() {
        return PublishedListingID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public String getGraveInscription() {
        return graveInscription;
    }

    public String getGraveNumber() {
        return graveNumber;
    }

    public String getImageOfBurialSite() {
        return imageOfBurialSite;
    }

    public String getLastKnownContactName() {
        return lastKnownContactName;
    }

    public String getLastKnownContactNumber() {
        return lastKnownContactNumber;
    }

    public Long getCemeteryID() {
        return cemeteryID;
    }

    public Long getSubscriberApprovedID() {
        return subscriberApprovedID;
    }

    public Long getSubscriberSubmitID() {
        return subscriberSubmitID;
    }

    public List<PersonOtherNames> getNames() {
        return names;
    }
    
    public static class Builder{
        Long PublishedListingID;
        String firstName;
        String surname;
        String maidenName;
        String gender;
        String dateOfBirth;
        String dateOfDeath;
        String graveInscription;
        String graveNumber;
        String imageOfBurialSite; // will need to be done in binary or saved in a online directory 
        String lastKnownContactName;
        String lastKnownContactNumber;
        Long cemeteryID;
        Long subscriberApprovedID;
        Long subscriberSubmitID;
        List<PersonOtherNames> names;

        public Builder setPublishedListingID(Long PublishedListingID) {
            this.PublishedListingID = PublishedListingID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setMaidenName(String maidenName) {
            this.maidenName = maidenName;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder setDateOfDeath(String dateOfDeath) {
            this.dateOfDeath = dateOfDeath;
            return this;
        }

        public Builder setGraveInscription(String graveInscription) {
            this.graveInscription = graveInscription;
            return this;
        }

        public Builder setGraveNumber(String graveNumber) {
            this.graveNumber = graveNumber;
            return this;
        }

        public Builder setImageOfBurialSite(String imageOfBurialSite) {
            this.imageOfBurialSite = imageOfBurialSite;
            return this;
        }

        public Builder setLastKnownContactName(String lastKnownContactName) {
            this.lastKnownContactName = lastKnownContactName;
            return this;
        }

        public Builder setLastKnownContactNumber(String lastKnownContactNumber) {
            this.lastKnownContactNumber = lastKnownContactNumber;
            return this;
        }

        public Builder setCemeteryID(Long cemeteryID) {
            this.cemeteryID = cemeteryID;
            return this;
        }

        public Builder setSubscriberApprovedID(Long memberApprovedID) {
            this.subscriberApprovedID = memberApprovedID;
            return this;
        }

        public Builder setSubscriberSubmitID(Long subscriberSubmitID) {
            this.subscriberSubmitID = subscriberSubmitID;
            return this;
        }

        public Builder setNames(List<PersonOtherNames> names) {
            this.names = names;
            return this;
        }
        
        
        
        public PublishedDeceasedListing build(){
            return new PublishedDeceasedListing(this);
        }
        
    public Builder PublishedDeceasedListing(PublishedDeceasedListing aThis) {
       this.PublishedListingID = aThis.PublishedListingID;
       this.cemeteryID = aThis.cemeteryID;
       this.dateOfBirth = aThis.dateOfBirth;
       this.dateOfDeath = aThis.dateOfDeath;
       this.firstName = aThis.firstName;
       this.gender = aThis.gender;
       this.graveInscription = aThis.graveInscription;
       this.graveNumber = aThis.graveNumber;
       this.imageOfBurialSite = aThis.imageOfBurialSite;
       this.lastKnownContactName = aThis.lastKnownContactName;
       this.lastKnownContactNumber = aThis.lastKnownContactNumber;
       this.surname = aThis.surname;
       this.maidenName = aThis.maidenName;
       this.subscriberApprovedID = aThis.subscriberApprovedID;
       this.subscriberSubmitID = aThis.subscriberSubmitID;
       this.names = aThis.names;
       return this;
    } 
        
    }
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.PublishedListingID);
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
        final PublishedDeceasedListing other = (PublishedDeceasedListing) obj;
        if (!Objects.equals(this.PublishedListingID, other.PublishedListingID)) {
            return false;
        }
        return true;
    }
}
