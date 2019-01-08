/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.repositories;

import com.devcontact.entity.model.ContactDetail;
import com.devcontact.entity.model.DeveloperCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Bruno
 */
public interface ContactDetailRepository extends CrudRepository<ContactDetail, Long> {

    Optional<ContactDetail> findContactDetailByEmail(String email);

    List<ContactDetail> findByDeveloperCategory(DeveloperCategory developerCategory);
}
