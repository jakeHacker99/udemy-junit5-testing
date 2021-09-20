package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {


    public static final String REDIRECT_OWNERS_124 = "redirect:/owners/124";
    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult result;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;


    @BeforeEach
    void setUp() {
        lenient().when(service.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .thenAnswer(invocation -> {

                    List<Owner> owners = new ArrayList<>();

                    String name = invocation.getArgument(0);


                    if (name.equals("%babian%")) {
                        owners.add(new Owner(1l, "Racket", "Danne"));
                        return owners;
                    } else if (name.equals("%DontFindMe%")) {
                        return owners;
                    } else if (name.equals("%findMe%")) {
                        Owner owner  = new Owner(1l, "10an", "snabbis");
                        Owner owner2  = new Owner(2l, "10ans kompis", "seg");

                        return owners;
                    }
                    throw new RuntimeException("invalid Argument ");
                });
    }


    @Test
    void processFindFormWildcardFound() {
        //            given
        Owner owner = new Owner(1l, "captain", "FindMe");
        List<Owner> ownerList = new ArrayList<>();

//            when
        String viewName = controller.processFindForm(owner, result, null);

//            then
        assertThat("%findMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }


    @Test
    void processFindFormWildcardNotFound() {
        //            given
        Owner owner = new Owner(1l, "captain", "DontFindMe");
        List<Owner> ownerList = new ArrayList<>();

//            when
        String viewName = controller.processFindForm(owner, result, null);

//            then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualTo(viewName);
    }

    @Test
    void processFindFormWildcardCaptor() {
        //            given
        Owner owner = new Owner(24L, "captain", "babian");
        List<Owner> ownerList = new ArrayList<>();

//            when
        String viewName = controller.processFindForm(owner, result, null);

//            then
        assertThat("%babian%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/findOwners").isEqualTo(viewName);

    }

    @Test
    void processCreationFormHasError() {
//      given
        Owner owner = new Owner(124L, "jakob", "yaro");
        given(result.hasErrors()).willReturn(true);


//        when
        String viewName = controller.processCreationForm(owner, result);

//      then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);

    }

    @Test
    void processCreationFormHasNoError() {
//      given
        Owner owner = new Owner(124L, "jakob", "yaro");
        given(result.hasErrors()).willReturn(false);
        given(service.save(any(Owner.class))).willReturn(owner);

//        when
        String viewName = controller.processCreationForm(owner, result);

//      then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_124);

    }
}