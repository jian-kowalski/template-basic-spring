package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.datasources.mapper.PetMapperDatasource;
import com.jiankowalski.basicspring.datasources.repository.PetRepository;
import com.jiankowalski.basicspring.domain.Pet;
import com.jiankowalski.basicspring.domain.exception.DomainException;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetDatasource {

    private final PetRepository petRepository;
    private final PetMapperDatasource petMapperDatasource;

    public PetDatasource(PetRepository petRepository) {
        this.petRepository = petRepository;
        this.petMapperDatasource = PetMapperDatasource.INSTANCE;
    }

    public Pet savePet(Pet pet) {
        try {
            return petMapperDatasource.petEntityToPet(petRepository.save(petMapperDatasource.petToPetEntity(pet)));
        } catch (Exception e) {
            throw new DomainException("Erro ao salvar o Pet", e);
        }
    }

    public Pet findPet(Long id) {
        return petMapperDatasource.petEntityToPet(
                petRepository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("pet", id);
                })
        );
    }

    public List<Pet> findAllPets() {
        return petMapperDatasource.petsToPetModels(petRepository.findAll());
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
