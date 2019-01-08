/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.entity.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Bruno
 */
@Entity
public class ContactDetail extends AuditModel implements Serializable {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "skype_id")
    private String skypeId;

    @Column(name = "mailing_address")
    private String mailingAddress;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "developer_category", nullable = false)
    private DeveloperCategory developerCategory;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public DeveloperCategory getDeveloperCategory() {
        return developerCategory;
    }

    public void setDeveloperCategory(DeveloperCategory developerCategory) {
        this.developerCategory = developerCategory;
    }

}
