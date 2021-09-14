package com.company.section5.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest {

    @Test
    void dependentAsserions() {
//      given
        Owner owner = new Owner(1L, "Jakob", "Yaro");
        owner.setCity("högdalen");

//      smsa ;)

//      then
        owner.setTelephone("0724037155");
        assertAll("Properties Test",
                () ->
                        assertAll("Person Properties",
                                () -> assertEquals("Jakob", owner.getFirstName(), "fistname"),
                () -> assertEquals("Yaro", owner.getLastName(), "lastname")),
                () ->
                        assertAll("Owner Properties",
                                () -> assertEquals("högdalen", owner.getCity(), "getCity")),
                () -> assertEquals("0724037155", owner.getTelephone(), "phonenr"));
    }

}