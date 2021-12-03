package com.jiankowalski.basicspring.datasources.repository;

import com.jiankowalski.basicspring.datasources.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {
}
