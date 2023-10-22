package com.udacity.jdnd.course3.critter.CritterChronologer.service.Impl;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Customer;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.PetRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public Customer createCustomer(Customer customer, List<Long> petIds) {

        if(petIds == null) {
            customer.setPets(new ArrayList<>());
        } else {
            for (Long petId : petIds) {
                customer.getPets().add(petRepository.getOne(petId));
            }
        }
        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
