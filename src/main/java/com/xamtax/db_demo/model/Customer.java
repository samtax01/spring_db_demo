package com.xamtax.db_demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private long id;
    private String firstName,  lastName;
}
