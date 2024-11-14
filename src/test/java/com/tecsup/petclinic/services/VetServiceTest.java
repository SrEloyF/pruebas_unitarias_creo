package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VetServiceTest {
    @Mock
    private VetRepository vetRepository;
    @InjectMocks
    private VetService vetService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateVet() {
        String firstName = "Viva";
        String lastName = "Kanye West";
        Vet vet = new Vet(firstName, lastName);
        vet.setId(1);
        Mockito.when(vetRepository.save(vet)).thenReturn(vet);
        Vet createdVet = vetService.create(vet);
        assertNotNull(createdVet.getId());
        assertEquals(firstName, createdVet.getFirstName());
        assertEquals(lastName, createdVet.getLastName());
    }


    @Test
    public void testUpdateVet() {
        Vet vet = new Vet(1, "Kanye", "West");
        String updatedFirstName = "Eloy";
        String updatedLastName = "West";
        vet.setFirstName(updatedFirstName);
        vet.setLastName(updatedLastName);
        Mockito.when(vetRepository.save(vet)).thenReturn(vet);
        Vet updatedVet = vetService.update(vet);
        assertEquals(updatedFirstName, updatedVet.getFirstName());
        assertEquals(updatedLastName, updatedVet.getLastName());
    }

    @Test
    public void testFindVetById() {
        Integer vetId = 1;
        Vet expectedVet = new Vet(vetId, "Kim", "Ye");
        Mockito.when(vetRepository.findById(vetId)).thenReturn(Optional.of(expectedVet));
        Vet foundVet = vetService.findById(vetId);
        assertEquals(vetId, foundVet.getId());
        assertEquals("Kim", foundVet.getFirstName());
        assertEquals("Ye", foundVet.getLastName());
    }


    @Test
    public void testDeleteVet() {
        Integer vetId = 1;
        Vet vet = new Vet(vetId, "Dina", "Castillo");
        Mockito.doNothing().when(vetRepository).deleteById(vetId);
        vetService.delete(vetId);
        Mockito.verify(vetRepository, Mockito.times(1)).deleteById(vetId);
    }
}
