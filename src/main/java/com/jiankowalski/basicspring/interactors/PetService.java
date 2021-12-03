package com.jiankowalski.basicspring.interactors;

import com.jiankowalski.basicspring.datasources.PetDatasource;
import com.jiankowalski.basicspring.domain.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetDatasource petDatasource;

    public PetService(PetDatasource petDatasource) {
        this.petDatasource = petDatasource;
    }

    public Pet createPet(Pet pet) {
        return petDatasource.savePet(pet);
    }

    public Pet getPet(Long id) {
        return petDatasource.findPet(id);
    }

    public List<Pet> getAllPets() {
        return petDatasource.findAllPets();
    }

    public void deletePet(Long id) {
        petDatasource.deletePet(id);
    }
}
