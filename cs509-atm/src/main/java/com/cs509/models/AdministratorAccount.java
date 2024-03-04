package com.cs509.models;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class AdministratorAccount extends Account {

    @Autowired
    public AdministratorAccount() {
        super();
    }
}
