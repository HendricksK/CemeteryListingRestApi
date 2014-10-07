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
public class UserRole implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id // deaulft 
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userRoleID;
    int level;

    private UserRole(Builder aThis) {
        this.level = aThis.level;
        this.userRoleID = aThis.userRoleID;
    }
    
    private UserRole(UserRole aThis){
        this.level = aThis.level;
        this.userRoleID = aThis.userRoleID;
    }

    public UserRole() {
    }

    public Long getUserRoleID() {
        return userRoleID;
    }

    public int getLevel() {
        return level;
    }
        
    public static class Builder{
        Long userRoleID;
        int level;

        public Builder setUserRoleID(Long userRoleID) {
            this.userRoleID = userRoleID;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }
        
        public UserRole build(){
            return new UserRole(this);
        }
        
     public Builder UserRole(UserRole aThis){
        this.level = aThis.level;
        this.userRoleID = aThis.userRoleID;
        return this;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.userRoleID);
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
        final UserRole other = (UserRole) obj;
        if (!Objects.equals(this.userRoleID, other.userRoleID)) {
            return false;
        }
        return true;
    }
    
    
}
