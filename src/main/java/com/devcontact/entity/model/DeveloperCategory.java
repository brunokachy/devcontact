/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devcontact.entity.model;

/**
 *
 * @author Bruno
 */
public enum DeveloperCategory {

    BACK_END_DEVELOPER("Backend Developer"),
    FRONT_END_DEVELOPER("Frontend Developer"),
    SOFTWARE_ARCHITECT("Software Architect"),
    MOBILE_DEVELOPER("Mobile Developer"),
    DATABASE_ENGINEER("Database Engineer");

    private final String category;

    DeveloperCategory(String category) {
        this.category = category;
    }

    public String category() {
        return category;
    }
}
