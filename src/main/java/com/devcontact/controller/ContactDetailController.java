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
import com.devcontact.rest.model.ContactDetailRequest;
import com.devcontact.rest.model.ContactDetailResponse;
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
    public ResponseEntity<ApiResponse<ContactDetailResponse>> register(@RequestBody ContactDetailRequest contactDetailRequest) {

        if (StringUtils.isEmpty(contactDetailRequest.getEmail()) || StringUtils.isEmpty(contactDetailRequest.getDeveloperCategory())) {
            throw new ResourceException("Missing required details.");
        }

        String contactEmail = contactDetailRequest.getEmail();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailByEmail(contactEmail);

        if (optContactDetail.isPresent()) {
            throw new ResourceException("Contact already exist.");
        }

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setActive(Boolean.TRUE);
        DeveloperCategory developerCategory = Stream.of(DeveloperCategory.values()).filter(d -> d.category().equalsIgnoreCase(contactDetailRequest.getDeveloperCategory())).findFirst().get();
        contactDetail.setDeveloperCategory(developerCategory);
        contactDetail.setEmail(contactEmail);
        contactDetail.setFirstName(contactDetailRequest.getFirstName());
        contactDetail.setLastName(contactDetailRequest.getLastName());
        contactDetail.setMailingAddress(contactDetailRequest.getMailingAddress());
        contactDetail.setPhoneNumber(contactDetailRequest.getPhoneNumber());
        contactDetail.setSkypeId(contactDetailRequest.getSkypeId());

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailResponse> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail registered successfully");
            ContactDetailResponse response = new ContactDetailResponse();
            response.setId(contactDetail.getId());
            apiResponse.setData(response);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailResponse>> update(@RequestBody ContactDetailRequest contactDetailRequest) {

        if (StringUtils.isEmpty(contactDetailRequest.getDeveloperCategory()) || StringUtils.isEmpty(contactDetailRequest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRequest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();
        DeveloperCategory developerCategory = Stream.of(DeveloperCategory.values()).filter(d -> d.category().equalsIgnoreCase(contactDetailRequest.getDeveloperCategory())).findFirst().get();
        contactDetail.setDeveloperCategory(developerCategory);
        contactDetail.setFirstName(contactDetailRequest.getFirstName());
        contactDetail.setLastName(contactDetailRequest.getLastName());
        contactDetail.setMailingAddress(contactDetailRequest.getMailingAddress());
        contactDetail.setPhoneNumber(contactDetailRequest.getPhoneNumber());
        contactDetail.setSkypeId(contactDetailRequest.getSkypeId());

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailResponse> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail updated successfully");
            ContactDetailResponse response = new ContactDetailResponse();
            response.setId(contactDetail.getId());
            apiResponse.setData(response);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/deactivate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailResponse>> deactivate(@RequestBody ContactDetailRequest contactDetailRequest) {

        if (StringUtils.isEmpty(contactDetailRequest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRequest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();
        contactDetail.setActive(Boolean.FALSE);

        try {
            contactDetailService.registerContactDetail(contactDetail);
            ApiResponse<ContactDetailResponse> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail deactivated successfully");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @PostMapping(path = "/delete", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<ContactDetailResponse>> delete(@RequestBody ContactDetailRequest contactDetailRequest) {

        if (StringUtils.isEmpty(contactDetailRequest.getId())) {
            throw new ResourceException("Missing required details.");
        }

        Long id = contactDetailRequest.getId();
        Optional<ContactDetail> optContactDetail = contactDetailService.getContactDetailyId(id);

        if (!optContactDetail.isPresent()) {
            throw new ResourceException("Contact detail does not exist.");
        }

        ContactDetail contactDetail = optContactDetail.get();

        try {
            contactDetailService.deleteContactDetail(contactDetail);
            ApiResponse<ContactDetailResponse> apiResponse = new ApiResponse<>();
            apiResponse.setStatus("Success");
            apiResponse.setMessage("Contact detail deleted successfully");
            apiResponse.setData(null);
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
            if (contactDetails.size() > 0) {
                apiResponse.setMessage("Data retrieved successfully");
            } else {
                apiResponse.setMessage("No data available");
            }
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
            if (contactDetails.isPresent()) {
                apiResponse.setStatus("Success");
                apiResponse.setMessage("Contact detail retrieved successfully");
            }else{
                apiResponse.setStatus("Failure");
                apiResponse.setMessage("Contact detail not retrieved");
            }
            apiResponse.setData(contactDetails.orElse(null));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
