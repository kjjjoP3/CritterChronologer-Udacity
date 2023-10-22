package com.udacity.jdnd.course3.critter.CritterChronologer.service;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Pet;

import java.util.List;

public interface PetService {

    Pet createPet(Pet pet, Long ownerId);
    Pet findById(Long ids);

    List<Pet> getPetsByOwner(Long ownerId);

    List<Pet> findAllById(List<Long> ids);
}
