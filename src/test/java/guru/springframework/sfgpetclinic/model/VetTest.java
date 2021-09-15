package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VetTest {
    Set<Speciality> specialities = new HashSet<Speciality>();
    Vet vet = new Vet(1L, "Jakob", "Yaro", specialities);
    Speciality speciality = new Speciality();


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Firstname")
    void getFirstName() {
        String firstName = vet.getFirstName();
        assertEquals("Jakob", firstName, "Firstname");
    }


    @Test
    @DisplayName("LastName - get")
    void getLastName() {
        String lastName = vet.getLastName();
        assertEquals("Yaro", lastName, "Lastname");
    }

    @Test
    @DisplayName("LastName -set")
    void setLastName() {

//      given
        String lastName = vet.getLastName();

//      when
        vet.setLastName("Yaro12");

//      then
        assertNotEquals(vet.getLastName(), lastName);
        assertEquals("Yaro12", vet.getLastName());

    }

    @Test
    @DisplayName("Specialties")
    void testSpecialties() {
        String message = " riktigt driftig på java //jake";
        speciality.setDescription(message);
        String expectedSpecialties = speciality.getDescription();

        assertEquals(message, expectedSpecialties);


    }

}