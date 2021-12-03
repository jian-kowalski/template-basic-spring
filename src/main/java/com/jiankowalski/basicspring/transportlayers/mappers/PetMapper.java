package com.jiankowalski.basicspring.transportlayers.mappers;

import com.jiankowalski.basicspring.domain.Pet;
import com.jiankowalski.openapi.model.PetInput;
import com.jiankowalski.openapi.model.PetModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    Pet petInputToPet(PetInput newPet);

    PetModel petToPetModel(Pet pet);

    List<PetModel> petsToPetModels(List<Pet> pets);

}
