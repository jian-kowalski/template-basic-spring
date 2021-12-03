package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.datasources.config.DatasourceConfiguration;
import com.jiankowalski.basicspring.datasources.repository.PetRepository;
import com.jiankowalski.basicspring.domain.Pet;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@EnableJpaRepositories(basePackages = "com.jiankowalski.basicspring.datasources.entity")
@Import({PetDatasource.class, DatasourceConfiguration.class})
class PetDatasourceTest {

    private static Long ID_INEXISTENTE = -999L;
    @Autowired
    private PetDatasource petDatasource;

    @Autowired
    private PetRepository petRepository;

    @Test
    void deveSalvarPetComSucesso() {
        Pet petSalvo = petDatasource.savePet(PetFactory.criarPetValido());
        assertThat(petSalvo).satisfies((pet -> {
            assertThat(pet.getName()).isEqualTo("scoob");
            assertThat(pet.getTag()).isEqualTo("1234-avc");
        }));
    }

    @Test
    void findPet() {
        Pet petSalvo = petDatasource.savePet(PetFactory.criarPetValido());
        assertThat(petDatasource.findPet(petSalvo.getId())).satisfies((pet -> {
            assertThat(pet.getName()).isEqualTo("scoob");
            assertThat(pet.getTag()).isEqualTo("1234-avc");
        }));
    }

    @Test
    void findAllPets() {
        petDatasource.savePet(PetFactory.criarPetValido());
        petDatasource.savePet(PetFactory.criarPetValido());
        assertThat(petDatasource.findAllPets()).satisfies((pets -> {
            assertThat(pets.size()).isEqualTo(2);
            assertThat(pets).isNotEmpty();
        }));
    }

    @Test
    void deletePet() {
        Pet petSalvo = petDatasource.savePet(PetFactory.criarPetValido());
        assertThat(petDatasource.findPet(petSalvo.getId())).satisfies((pet -> {
            assertThat(pet.getName()).isEqualTo("scoob");
            assertThat(pet.getTag()).isEqualTo("1234-avc");
        }));
        assertDoesNotThrow(() -> petDatasource.deletePet(petSalvo.getId()));
    }

    @Test
    void deveLancarExceptionAoConsultarPetInexistente() {
        assertThat(assertThrows(NotFoundException.class, () -> {
            petDatasource.findPet(ID_INEXISTENTE);
        })).satisfies((e) -> {
            assertThat(e.getMessage()).isEqualTo("Record not found for pet with id -999");
        });
    }
}