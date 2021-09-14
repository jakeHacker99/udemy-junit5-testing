package com.company.section5;

import com.company.section5.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void groupedAssertions(){
//        given
        Person person = new Person(1L, "Joe", "Buck");

//      then
        assertAll("test all set",
                () -> assertEquals("Joe " , person.getFirstName(), "fail in firstanme"),
                () -> assertEquals("Buck", person.getLastName(), "fail in firstanme"));
    }
}
