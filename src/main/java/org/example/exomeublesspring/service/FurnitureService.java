package org.example.exomeublesspring.service;

import org.example.exomeublesspring.dao.FurnitureRepository;
import org.example.exomeublesspring.entity.Furniture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> findAll() {
        return furnitureRepository.findAllByOrderByIdAsc();
    }

    public Furniture save(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public Furniture findById(int id) {
        return furnitureRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        furnitureRepository.deleteById(id);
    }
}
