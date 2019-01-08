/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.service;

import com.devcontact.entity.model.ContactDetail;
import com.devcontact.entity.model.DeveloperCategory;
import com.devcontact.repositories.ContactDetailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno
 */
@Service
public class ContactDetailServiceImpl implements ContactDetailService {

    @Autowired
    ContactDetailRepository contactDetailRepository;

    @Override
    public Optional<ContactDetail> getContactDetailByEmail(String email) {
        return contactDetailRepository.findContactDetailByEmail(email);
    }

    @Override
    public List<ContactDetail> getContactDetailByDeveloperCategory(DeveloperCategory developerCategory) {
        return contactDetailRepository.findByDeveloperCategory(developerCategory);
    }

    @Override
    public void registerContactDetail(ContactDetail contactDetail) {
        contactDetailRepository.save(contactDetail);
    }

    @Override
    public Optional<ContactDetail> getContactDetailyId(Long id) {
        return contactDetailRepository.findById(id);
    }

    @Override
    public void deleteContactDetail(ContactDetail contactDetail) {
        contactDetailRepository.delete(contactDetail);
    }

    @Override
    public void deleteContactDetailById(Long id) {
        contactDetailRepository.deleteById(id);
    }

}
