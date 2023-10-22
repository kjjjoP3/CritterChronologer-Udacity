package com.udacity.jdnd.course3.critter.CritterChronologer.pet;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Customer;
import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Pet;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        long ownerId = petDTO.getOwnerId();
        return toPetDTO(petService.createPet(pet, ownerId));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findById(petId);
        return toPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOs = new ArrayList<>();
        for (Pet pet : pets) {
            PetDTO petDTO = new PetDTO();
            BeanUtils.copyProperties(pet, petDTO);
            petDTOs.add(petDTO);
        }
        return petDTOs;
    }


    private PetDTO toPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
}


