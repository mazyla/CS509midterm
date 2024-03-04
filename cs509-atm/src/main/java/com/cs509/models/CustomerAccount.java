package com.cs509.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@DiscriminatorValue("CUSTOMER")
public class CustomerAccount extends Account {

    @Autowired
    public CustomerAccount() {
        super();
    }
}
