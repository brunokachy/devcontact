/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact;

import com.devcontact.exceptions.ResourceException;
import java.util.AbstractMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Bruno
 */
@ControllerAdvice(annotations = {RestController.class})
public class RestApiControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RestApiControllerAdvice.class);

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity handleRuntimeException(Exception exception) {
        log.error("RuntimeException: ", exception.getMessage());
        AbstractMap.SimpleEntry<String, String> response
                = new AbstractMap.SimpleEntry<>("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity handleKnownException(ResourceException e) {
        log.info("error message: {}", e.getMessage());
        AbstractMap.SimpleEntry<String, String> response
                = new AbstractMap.SimpleEntry<>("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
