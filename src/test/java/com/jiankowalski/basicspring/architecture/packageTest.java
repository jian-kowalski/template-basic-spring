package com.jiankowalski.basicspring.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.jiankowalski.basicspring")
public class packageTest {

    @ArchTest
    static final ArchRule classesAnotadasComEntityDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().resideInAPackage("..datasources..entity..")
            .as("Classes anotadas com @Entity deve fica no pacote {datasources.entity} ");

    @ArchTest
    static final ArchRule classesAnotadasComRepositoryDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().resideInAPackage("..datasources..repository..")
            .as("Classes anotadas com @Repository deve fica no pacote {datasources.repository} ");

    @ArchTest
    static final ArchRule classesAnotadasComServiceDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(Service.class)
            .should().resideInAPackage("..interactors..")
            .as("Classes anotadas com @Service deve fica no pacote {interactors} ");

    @ArchTest
    static final ArchRule classesAnotadasComRestControllerDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..transportlayers..")
            .as("Classes anotadas com @RestController deve fica no pacote {transportlayers} ");
}
