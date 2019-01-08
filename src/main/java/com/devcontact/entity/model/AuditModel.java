/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.entity.model;

import java.sql.Timestamp;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Bruno
 */
@MappedSuperclass
public abstract class AuditModel extends BasicModel {
    
    private Timestamp dateCreated;
    
    private Timestamp lastUpdated;
    

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        lastUpdated = new Timestamp(System.currentTimeMillis());
        if(dateCreated == null){
            dateCreated = new Timestamp(System.currentTimeMillis());
        }
    }
    
    
}
