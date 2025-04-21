package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;

import java.util.List;

public interface PetService {
    Pet save(Pet pet);
    Pet getPetById(Long petId);
    List<Pet> getAllPets();
    List<Pet> getAllPetByOwner(long ownerId);
}
