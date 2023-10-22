package com.udacity.jdnd.course3.critter.CritterChronologer.service;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer, List<Long> petIds);
    List<Customer> findAll();
}
