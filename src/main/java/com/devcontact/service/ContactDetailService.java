/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.service;

import com.devcontact.entity.model.ContactDetail;
import com.devcontact.entity.model.DeveloperCategory;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bruno
 */
public interface ContactDetailService {

    Optional<ContactDetail> getContactDetailByEmail(String email);

    List<ContactDetail> getContactDetailByDeveloperCategory(DeveloperCategory developerCategory);

    void registerContactDetail(ContactDetail contactDetail);
    
    void deleteContactDetail(ContactDetail contactDetail);
    
    void deleteContactDetailById(Long id);

    Optional<ContactDetail> getContactDetailyId(Long id);
}
