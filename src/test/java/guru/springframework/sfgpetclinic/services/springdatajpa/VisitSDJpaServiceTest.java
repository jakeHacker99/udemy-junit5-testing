package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    Visit visit =new Visit();

    @Mock
    VisitRepository repository;


    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(visit);

        when(repository.findAll()).thenReturn(visitSet);
        Set<Visit> foundVisits = service.findAll();

        then(repository).should().findAll();
        assertThat(foundVisits).hasSize(1);

    }

    @Test
    void findById() {
        service.findById(anyLong());

        then(repository).should().findById(anyLong());

    }



    @Test
    void save() {
        service.save(visit);
        then(repository).should().save(any(Visit.class));

    }

    @Test
    void delete() {
        service.delete(visit);
        then(repository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        then(repository).should().deleteById(anyLong());
    }
}