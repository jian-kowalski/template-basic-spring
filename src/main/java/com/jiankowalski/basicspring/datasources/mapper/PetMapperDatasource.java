package com.jiankowalski.basicspring.datasources.mapper;

import com.jiankowalski.basicspring.datasources.entity.PetEntity;
import com.jiankowalski.basicspring.domain.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PetMapperDatasource {

    PetMapperDatasource INSTANCE = Mappers.getMapper(PetMapperDatasource.class);

    PetEntity petToPetEntity(Pet pet);

    Pet petEntityToPet(PetEntity pet);

    List<Pet> petsToPetModels(List<PetEntity> pets);
    
}
