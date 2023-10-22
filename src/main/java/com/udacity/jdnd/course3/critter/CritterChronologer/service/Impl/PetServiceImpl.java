package com.udacity.jdnd.course3.critter.CritterChronologer.service.Impl;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Customer;
import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Pet;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.PetRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Pet createPet(Pet pet, Long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        if (customer.getPets() == null) {
            customer.setPets(new ArrayList<>());
        }
        Pet petCreated = petRepository.save(pet);
        if(petCreated != null) {
            customer.getPets().add(petCreated);
            customerRepository.save(customer);
        }
        return petCreated;
    }

    @Override
    public Pet findById(Long ids) {
        return petRepository.getOne(ids);
    }

    @Override
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.getAllByCustomerId(ownerId);
    }

    @Override
    public List<Pet> findAllById(List<Long> ids) {
        return petRepository.findAllById(ids);
    }
}
