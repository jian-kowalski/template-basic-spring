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
public class NameTest {

    @ArchTest
    static final ArchRule classesAnotadasComEntityDeveTerSufixoEntity = classes()
            .that().areAnnotatedWith(Entity.class)
            .should().haveSimpleNameEndingWith("Entity")
            .as("Classes anotadas com @Entity deve o sufixo Entity ");

    @ArchTest
    static final ArchRule classesAnotadasComRepositoryDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().haveSimpleNameEndingWith("Repository")
            .as("Classes anotadas com @Entity deve o sufixo repository ");

    @ArchTest
    static final ArchRule classesAnotadasComServiceDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(Service.class)
            .should().haveSimpleNameEndingWith("Service")
            .as("Classes anotadas com @Entity deve o sufixo Service ");

    @ArchTest
    static final ArchRule classesAnotadasComRestControllerDeveFicarNoPacoteEnties = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().haveSimpleNameEndingWith("Controller")
            .as("Classes anotadas com @Entity deve o sufixo Controller ");
}
