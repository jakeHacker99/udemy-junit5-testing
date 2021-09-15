package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
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
        assertThat(owner.getCity(), is("högdalen"));
    }

}