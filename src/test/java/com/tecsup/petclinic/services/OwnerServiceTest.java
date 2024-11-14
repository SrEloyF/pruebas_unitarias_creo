package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OwnerServiceTest {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private OwnerService ownerService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateOwner() {
		String firstName = "Jaime";
		String lastName = "Perez";
		String address = "Siempre viva 123";
		String city = "Springfield";
		String telephone = "13445";
		Owner owner = new Owner(firstName, lastName, address, city, telephone);
		owner.setId(1);
		Mockito.when(ownerRepository.save(owner)).thenReturn(owner);
		Owner createdOwner = ownerService.create(owner);
		assertNotNull(createdOwner.getId());
		assertEquals(firstName, createdOwner.getFirstName());
		assertEquals(lastName, createdOwner.getLastName());
	}

	@Test
	public void testFindOwnerById() {
		Integer ID = 1;
		String NAME = "Jaime";
		Owner owner = new Owner(ID, "Jaime", "Perez", "Address", "City", "1234567890");
		Mockito.when(ownerRepository.findById(ID)).thenReturn(java.util.Optional.of(owner));
		Long id = Long.valueOf(ID);
		Owner foundOwner = ownerService.findById(id);
		assertEquals(NAME, foundOwner.getFirstName());
	}

	@Test
	public void testUpdateOwner() {
		Integer ID = 1;
		String oldFirstName = "Jaime";
		String oldLastName = "Perez";
		String newFirstName = "Carlos";
		String newLastName = "Lopez";
		Owner owner = new Owner(ID, oldFirstName, oldLastName, "Address", "City", "1234567890");
		Mockito.when(ownerRepository.findById(ID)).thenReturn(java.util.Optional.of(owner));
		owner.setFirstName(newFirstName);
		owner.setLastName(newLastName);
		Mockito.when(ownerRepository.save(owner)).thenReturn(owner);
		Owner updatedOwner = ownerService.update(owner);
		assertEquals(newFirstName, updatedOwner.getFirstName());
		assertEquals(newLastName, updatedOwner.getLastName());
	}

	@Test
	public void testDeleteOwner() {
		Integer ID = 1;
		Owner owner = new Owner(ID, "Jaime", "Perez", "Address", "City", "1234567890");
		Mockito.when(ownerRepository.findById(ID)).thenReturn(java.util.Optional.of(owner));
		ownerService.delete(ID);
		Mockito.when(ownerRepository.findById(ID)).thenReturn(java.util.Optional.empty());
		Long id = Long.valueOf(ID);
		assertThrows(OwnerNotFoundException.class, () -> ownerService.findById(id));
	}

}
