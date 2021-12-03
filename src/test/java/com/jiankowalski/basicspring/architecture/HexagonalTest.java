package com.jiankowalski.basicspring.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.jiankowalski.basicspring")
public class HexagonalTest {

    @ArchTest
    public static final ArchRule architectureValidator = Architectures.layeredArchitecture()
            .layer("Transportlayers").definedBy("..transportlayers..")
            .layer("Interactors").definedBy("..interactors..")
            .layer("Domain").definedBy("..domain..")
            .layer("Datasources").definedBy("..datasources..")
            .whereLayer("Interactors").mayOnlyBeAccessedByLayers("Transportlayers")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Transportlayers", "Interactors", "Datasources");

}
