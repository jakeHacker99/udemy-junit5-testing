package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }


    @ParameterizedTest(name="{displayName} -[{index}] {arguments}")
    @DisplayName("Csv Input Test")
    @CsvSource({"FL, 1,2,4",
            "HD, 3,2,5",
            "RGS, 6,2,5"

    })

    void csvInputTest(String  stateName, int val1, int val2 ) {
        System.out.println(stateName + " = " + val1 + " : " + val2);

    }


    @DisplayName("CSV From File Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1 ) {
        System.out.println(stateName + " = " + val1 );
    }
    @DisplayName("Method Provider")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")

    void fromMethodTest(String stateName, int val1 ) {
        System.out.println(stateName + " = " + val1 );
    }
    static Stream<Arguments> getArgs(){
        return Stream.of(Arguments.of("FL", 124),
                Arguments.of("HD", 325),
                Arguments.of("RGS", 625)
                );

    }


    @ParameterizedTest(name="{displayName} - [{index}] {arguments} ")
    @DisplayName("")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1) {
        System.out.println(stateName + " = " + val1);
    }
}