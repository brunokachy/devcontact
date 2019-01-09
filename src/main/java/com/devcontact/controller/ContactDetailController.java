/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.controller;

import com.devcontact.ApiResponse;
import com.devcontact.entity.model.ContactDetail;
import com.devcontact.entity.model.DeveloperCategory;
import com.devcontact.exceptions.ResourceException;
import com.devcontact.rest.model.ContactDetailRest;
import com.devcontact.service.ContactDetailService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bruno
 */
@RestController
@RequestMapping("/devcontact/api/contactDetail")
public class ContactDetailController {

    @Autowired
    private ContactDetailService contactDetailService;

    @PostMapping(path = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailRest>> register(@RequestBody ContactDetailRest contactDetailRest) {

        if (StringUtils.isEmpty(contactDetailRest.getEmail()) || StringUtils.isEmpty(contactDetailRest.getDeveloperCategory())) {
            throw new ResourceException("Missing required details.");
        }

        String contactEmail = contactDetailRest.getEmail();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailByEmail(contactEmail);

        if (optContactDetail.isPresent()) {
            throw new ResourceException("Contact already exist.");
        }

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setActive(Boolean.TRUE);
        DeveloperCategory developerCategory = Stream.of(DeveloperCategory.values()).filter(d -> d.category().equalsIgnoreCase(contactDetailRest.getDeveloperCategory())).findFirst().get();
        contactDetail.setDeveloperCategory(developerCategory);
        contactDetail.setEmail(contactEmail);
        contactDetail.setFirstName(contactDetailRest.getFirstName());
        contactDetail.setLastName(contactDetailRest.getLastName());
        contactDetail.setMailingAddress(contactDetailRest.getMailingAddress());
        contactDetail.setPhoneNumber(contactDetailRest.getPhoneNumber());
        contactDetail.setSkypeId(contactDetailRest.getSkypeId());

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail registered successfully");
            apiResponse.setData(contactDetailRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailRest>> update(@RequestBody ContactDetailRest contactDetailRest) {

        if (StringUtils.isEmpty(contactDetailRest.getDeveloperCategory()) || StringUtils.isEmpty(contactDetailRest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();
        DeveloperCategory developerCategory = Stream.of(DeveloperCategory.values()).filter(d -> d.category().equalsIgnoreCase(contactDetailRest.getDeveloperCategory())).findFirst().get();
        contactDetail.setDeveloperCategory(developerCategory);
        contactDetail.setFirstName(contactDetailRest.getFirstName());
        contactDetail.setLastName(contactDetailRest.getLastName());
        contactDetail.setMailingAddress(contactDetailRest.getMailingAddress());
        contactDetail.setPhoneNumber(contactDetailRest.getPhoneNumber());
        contactDetail.setSkypeId(contactDetailRest.getSkypeId());

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail updated successfully");
            apiResponse.setData(contactDetailRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/deactivate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailRest>> deactivate(@RequestBody ContactDetailRest contactDetailRest) {

        if (StringUtils.isEmpty(contactDetailRest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();
        contactDetail.setActive(Boolean.FALSE);

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail deactivated successfully");
            apiResponse.setData(contactDetailRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/delete", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailRest>> delete(@RequestBody ContactDetailRest contactDetailRest) {

        if (StringUtils.isEmpty(contactDetailRest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();

        try {
            contactDetailService.deleteContactDetail(contactDetail);
            ApiResponse<ContactDetailRest> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail deleted successfully");
            apiResponse.setData(contactDetailRest);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @GetMapping(path = "/getContactByCategory", produces = "application/json")
    public ResponseEntity<ApiResponse<List<ContactDetail>>> getContactByCategory(@RequestParam String category) {

        if (StringUtils.isEmpty(category)) {
            throw new ResourceException("Missing required detail.");
        }
        DeveloperCategory developerCategory = Stream.of(DeveloperCategory.values()).filter(d -> d.category().equalsIgnoreCase(category)).findFirst().get();

        try {
            List<ContactDetail> contactDetails = contactDetailService.getContactDetailByDeveloperCategory(developerCategory);
            ApiResponse<List<ContactDetail>> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail retrieved successfully");
            apiResponse.setData(contactDetails);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @GetMapping(path = "/getContactByEmail", produces = "application/json")
    public ResponseEntity<ApiResponse<ContactDetail>> getContactByEmail(@RequestParam String email) {

        if (StringUtils.isEmpty(email)) {
            throw new ResourceException("Missing required detail.");
        }

        try {
            Optional<ContactDetail> contactDetails = contactDetailService.getContactDetailByEmail(email);
            ApiResponse<ContactDetail> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail retrieved successfully");
            apiResponse.setData(contactDetails.orElse(null));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
