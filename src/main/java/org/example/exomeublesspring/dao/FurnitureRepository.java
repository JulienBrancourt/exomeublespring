package org.example.exomeublesspring.dao;

import org.example.exomeublesspring.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    List<Furniture> findAllByOrderByIdAsc();
}
