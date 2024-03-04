package com.cs509.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class AdministratorAccount extends Account {

    @Autowired
    public AdministratorAccount() {
        super();
    }
}
