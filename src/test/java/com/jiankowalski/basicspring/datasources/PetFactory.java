package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.domain.Pet;
import com.jiankowalski.openapi.model.PetInput;

public class PetFactory {

    public static Pet criarPetValido() {
        return new Pet("scoob", "1234-avc", null);
    }

    public static PetInput criarPetInputValido() {
        PetInput pet = new PetInput();
        pet.setName("scoob");
        pet.setTag("1234-avc");
        return pet;
    }

    public static PetInput criarPetInputSemNome() {
        PetInput pet = new PetInput();
        pet.setTag("1234-avc");
        return pet;
    }
}
