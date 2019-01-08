/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.exceptions;

/**
 *
 * @author Bruno
 */
public class ResourceException extends RuntimeException {

    public ResourceException() {
        super();
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
