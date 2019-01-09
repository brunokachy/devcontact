/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.controller;

import com.devcontact.ApiResponse;
import com.devcontact.rest.model.ContactDetailRest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Bruno
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactDetailControllerTest {

    MockMvc mockMvc;

    @Mock
    private ContactDetailController contactDetailController;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(contactDetailController).build();
    }

    public ContactDetailControllerTest() {
    }

    /**
     * Test of register method, of class ContactDetailController.
     */
    @Test
    public void testRegister() {
        ContactDetailRest contactDetailRest = new ContactDetailRest();
        contactDetailRest.setDeveloperCategory("Frontend Developer");
        contactDetailRest.setEmail("oookafor@gmail.com");
        contactDetailRest.setFirstName("Bruno");
        contactDetailRest.setLastName("Okafor");
        contactDetailRest.setSkypeId("ookafor");

        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> contactDetail = getHttpEntity(contactDetailRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/devcontact/api/contactDetail/register", contactDetail, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());

    }

    @Test
    public void testRegisterContactDetailExist() {
        ContactDetailRest contactDetailRest = new ContactDetailRest();
        contactDetailRest.setDeveloperCategory("Frontend Developer");
        contactDetailRest.setEmail("oookafor@gmail.com");
        contactDetailRest.setFirstName("Bruno");
        contactDetailRest.setLastName("Okafor");
        contactDetailRest.setSkypeId("ookafor");

        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.BAD_REQUEST);

        HttpEntity<Object> contactDetail = getHttpEntity(contactDetailRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/devcontact/api/contactDetail/register", contactDetail, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());

    }

//    /**
//     * Test of update method, of class ContactDetailController.
//     */
    @Test
    public void testUpdate() {
        ContactDetailRest contactDetailRest = new ContactDetailRest();
        contactDetailRest.setDeveloperCategory("Frontend Developer");
        contactDetailRest.setEmail("oookafor@gmail.com");
        contactDetailRest.setFirstName("Bruno");
        contactDetailRest.setLastName("Okafor");
        contactDetailRest.setSkypeId("brunokafor");
        contactDetailRest.setId(2L);

        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> contactDetail = getHttpEntity(contactDetailRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/devcontact/api/contactDetail/update", contactDetail, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

//    /**
//     * Test of deactivate method, of class ContactDetailController.
//     */
    @Test
    public void testDeactivate() {
        ContactDetailRest contactDetailRest = new ContactDetailRest();
        contactDetailRest.setId(2L);

        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> contactDetail = getHttpEntity(contactDetailRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/devcontact/api/contactDetail/deactivate", contactDetail, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

//    /**
//     * Test of delete method, of class ContactDetailController.
//     */
    @Test
    public void testDelete() {
        ContactDetailRest contactDetailRest = new ContactDetailRest();
        contactDetailRest.setId(2L);

        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);

        HttpEntity<Object> contactDetail = getHttpEntity(contactDetailRest);
        ResponseEntity<Object> response = template.postForEntity(
                "/devcontact/api/contactDetail/delete", contactDetail, Object.class);

        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

//    /**
//     * Test of getContactByCategory method, of class ContactDetailController.
//     */
    @Test
    public void testGetContactByCategory() {
        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        ResponseEntity<?> response = template.getForEntity(
                "/devcontact/api/contactDetail/getContactByCategory?category=Frontend Developer", Object.class);
        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

//    /**
//     * Test of getContactByEmail method, of class ContactDetailController.
//     */
    @Test
    public void testGetContactByEmail() {
        ResponseEntity<ApiResponse<ContactDetailRest>> expResult = new ResponseEntity<>(new ApiResponse<>(), HttpStatus.OK);
        ResponseEntity<?> response = template.getForEntity(
                "/devcontact/api/contactDetail/getContactByEmail?email=brunoo.okafor@gmail.com", Object.class);
        assertEquals(expResult.getStatusCode(), response.getStatusCode());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
