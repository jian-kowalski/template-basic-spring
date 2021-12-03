package com.jiankowalski.basicspring.transportlayers;


import com.jiankowalski.basicspring.interactors.PetService;
import com.jiankowalski.basicspring.transportlayers.mappers.PetMapper;
import com.jiankowalski.openapi.api.PetsApi;
import com.jiankowalski.openapi.model.PetInput;
import com.jiankowalski.openapi.model.PetModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController implements PetsApi {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService) {
        this.petService = petService;
        this.petMapper = PetMapper.INSTANCE;
    }

    @Override
    public ResponseEntity<PetModel> addPet(PetInput petInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                petMapper.petToPetModel(petService.createPet(petMapper.petInputToPet(petInput)))
        );
    }

    @Override
    public ResponseEntity<PetModel> findPetById(Long id) {
        return ResponseEntity.ok(petMapper.petToPetModel(petService.getPet(id)));
    }

    @Override
    public ResponseEntity<List<PetModel>> findPets() {
        return ResponseEntity.ok(
                petMapper.petsToPetModels(petService.getAllPets())
        );
    }

    @Override
    public ResponseEntity<Void> deletePet(Long id) {
        petService.deletePet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
