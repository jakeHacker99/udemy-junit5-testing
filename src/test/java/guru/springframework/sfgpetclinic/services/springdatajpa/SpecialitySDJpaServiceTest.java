package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
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
}