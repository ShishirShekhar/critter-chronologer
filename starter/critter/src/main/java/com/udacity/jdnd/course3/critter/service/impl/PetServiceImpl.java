package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final CustomerService customerService;

    @Autowired
    public PetServiceImpl(
            PetRepository petRepository,
            CustomerService customerService
    ) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    @Override
    public Pet save(Pet pet) {
        Pet savedPet = petRepository.save(pet);

        Customer owner = savedPet.getOwner();
        owner.addPet(pet);
        customerService.save(owner);

        return savedPet;
    }

    @Override
    public Pet getPetById(Long petId) {
        return petRepository.findById(petId).orElse(null);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getAllPetByOwner(long ownerId) {
        return petRepository.findAllByOwnerId(ownerId);
    }
}
