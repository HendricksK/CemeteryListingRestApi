/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cemeterylistingsweb.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Kurvin Hendricks
 */
@Embeddable
public class Location implements Serializable{
    String country;
    String province_State;
    String district_state;
    String town;
    String cemeteryName;
    String locationOfCemetery;

    private Location(Builder aThis) {
        this.cemeteryName = aThis.cemeteryName;
        this.country = aThis.country;
        this.district_state = aThis.district_state;
        this.locationOfCemetery = aThis.locationOfCemetery;
        this.province_State = aThis.province_State;
        this.town = aThis.town;
    }
    
    private Location(Location aThis){
        this.cemeteryName = aThis.cemeteryName;
        this.country = aThis.country;
        this.district_state = aThis.district_state;
        this.locationOfCemetery = aThis.locationOfCemetery;
        this.province_State = aThis.province_State;
        this.town = aThis.town;
    }
    
    public Location(){};
    
    public String getCountry() {
        return country;
    }

    public String getProvince_State() {
        return province_State;
    }

    public String getDistrict_state() {
        return district_state;
    }

    public String getTown() {
        return town;
    }

    public String getCemeteryName() {
        return cemeteryName;
    }

    public String getLocationOfCemetery() {
        return locationOfCemetery;
    }
    
    public static class Builder{
       
        String country;
        String province_State;
        String district_state;
        String town;
        String cemeteryName;
        String locationOfCemetery;

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setProvince_State(String province_State) {
            this.province_State = province_State;
            return this;
        }

        public Builder setDistrict_state(String district_state) {
            this.district_state = district_state;
            return this;
        }

        public Builder setTown(String town) {
            this.town = town;
            return this;
        }

        public Builder setCemeteryName(String cemeteryName) {
            this.cemeteryName = cemeteryName;
            return this;
        }

        public Builder setLocationOfCemetery(String locationOfCemetery) {
            this.locationOfCemetery = locationOfCemetery;
            return this;
        }
        
        public Location build(){
            return new Location(this);
        }
    
    }
    
    
}
