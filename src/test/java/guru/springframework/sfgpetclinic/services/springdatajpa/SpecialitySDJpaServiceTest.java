package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.LenientStubber;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
@ExtendWith(MockitoExtension.class)

class SpecialitySDJpaServiceTest {

    @Mock()
    SpecialtyRepository repository;


    @InjectMocks
    SpecialitySDJpaService service;


    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(repository, times(2)).deleteById(anyLong());

    }


    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);


        then(repository).should(atLeastOnce()).deleteById(any());
    }

    @Test
    void deleteObjectBddTest() {
        Speciality speciality = new Speciality();

        service.delete(speciality);

        then(repository).should().delete(any(Speciality.class));
    }

    @Test
    void findByIdBddTest() {
//      given
        Speciality speciality = new Speciality();

//      when
        when(repository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpecialty = service.findById(1L);

//      then
        assertThat(foundSpecialty).isNotNull();
        verify(repository, times(1)).findById(any());
    }

    @Test
    void deleteObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(repository).delete(speciality);
    }

@Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(1L);
        service.deleteById(2L);
        service.deleteById(2L);
    service.deleteById(1L);

    verify(repository, atMost(4) ).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);

        verify(repository, never()).deleteById(124L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }


    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("ka booom!")).when(repository).delete(any());
        assertThrows(RuntimeException.class, () -> repository.delete(any(Speciality.class)));
        then(repository).should().delete(any());

    }


    @Test
    void testDoThrowBdd() {
        given(repository.findById(1L)).willThrow(new RuntimeException("ka booom baby booo!!!!"));

        assertThrows(RuntimeException.class, () -> service.findById(1L));


        then(repository).should().findById(1L);

    }


    @Test
    void testDeleteBdd() {
        willThrow(new RuntimeException("boom bro booom!!!")).given(repository).delete(any());

        assertThrows(RuntimeException.class, () -> repository.delete(any(Speciality.class)));
        then(repository).should().delete(any());

    }




    @Test
    void testSaveLambda() {
            final String MATCH_ME = "match_me";

            Speciality speciality  = new Speciality();
            speciality.setDescription(MATCH_ME);

            Speciality saveSpecialty = new Speciality();
            saveSpecialty.setId(1L);
//      given
            given(repository.save(argThat(arg  -> arg.getDescription().equals(MATCH_ME)))).willReturn(saveSpecialty);


            Speciality returnedSpeciality = service.save(speciality);

//      then
            assertThat(returnedSpeciality.getId()).isEqualTo(1L);

    }

    @Test
    void testSAveLambdaNoMatch() {
        final String MATCH_ME = "match_me";

        Speciality speciality  = new Speciality();
        speciality.setDescription("not a match");

        Speciality saveSpecialty = new Speciality();
        saveSpecialty.setId(1L);
//      given
                lenient().when(repository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).thenReturn(saveSpecialty);

        Speciality returnedSpeciality = service.save(speciality);

//      then
        
        assertNull(returnedSpeciality);
    }
}