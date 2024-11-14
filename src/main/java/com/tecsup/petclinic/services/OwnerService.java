package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findById(Long id) {
        if (id < Integer.MIN_VALUE || id > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Eloy: El valor de id está fuera del rango de Integer");
        }

        Integer idAsInteger = id.intValue();

        Optional<Owner> owner = ownerRepository.findById(idAsInteger);

        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new OwnerNotFoundException("Owner not found with ID: " + id);
        }
    }


    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public void delete(Integer id) {
        ownerRepository.deleteById(id);
    }
}
