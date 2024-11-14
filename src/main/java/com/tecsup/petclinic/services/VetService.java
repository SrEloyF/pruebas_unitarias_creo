package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }

    public Vet findById(Integer id) {
        Optional<Vet> vet = vetRepository.findById(id);
        if (vet.isPresent()) {
            return vet.get();
        } else {
            throw new RuntimeException("Veterinarian not found");
        }
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    public void delete(Integer id) {
        vetRepository.deleteById(id);
    }
}
