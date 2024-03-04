package com.cs509.models;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class CustomerAccount extends Account {

    @Autowired
    public CustomerAccount() {
        super();
    }
}
