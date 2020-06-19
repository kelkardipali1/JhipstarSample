package com.thoughtworks.basicapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.thoughtworks.basicapp");

        noClasses()
            .that()
                .resideInAnyPackage("com.thoughtworks.basicapp.service..")
            .or()
                .resideInAnyPackage("com.thoughtworks.basicapp.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.thoughtworks.basicapp.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
