package com.jiankowalski.basicspring.interactors;

import com.jiankowalski.basicspring.datasources.PetDatasource;
import com.jiankowalski.basicspring.datasources.PetFactory;
import com.jiankowalski.basicspring.domain.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {PetService.class})
class PetServiceTest {

    @Autowired
    private PetService petService;

    @MockBean
    private PetDatasource petDatasource;

    @Test
    void createPet() {
        Mockito.when(petDatasource.savePet(Mockito.any(Pet.class))).thenReturn(PetFactory.criarPetValido());
        Pet pet = petService.createPet(PetFactory.criarPetValido());
        assertThat(pet.getName()).isEqualTo("scoob");
    }

    @Test
    void getPet() {
        Mockito.when(petDatasource.findPet(Mockito.any(Long.class))).thenReturn(PetFactory.criarPetValido());
        Pet pet = petService.getPet(1L);
        assertThat(pet.getName()).isEqualTo("scoob");
    }

    @Test
    void getAllPets() {
        Mockito.when(petDatasource.findAllPets()).thenReturn(Arrays.asList(PetFactory.criarPetValido()));
        assertThat(petService.getAllPets()).satisfies(pets -> {
            assertThat(pets.size()).isEqualTo(1);
            assertThat(pets.get(0).getName()).isEqualTo("scoob");
        });
    }

    @Test
    void deletePet() {
        Mockito.when(petDatasource.findPet(Mockito.any(Long.class))).thenReturn(PetFactory.criarPetValido());
        Pet pet = petService.getPet(1L);
        assertDoesNotThrow(() -> {
            petService.deletePet(pet.getId());
        });

    }
}