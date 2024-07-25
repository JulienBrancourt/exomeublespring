package org.example.exomeublesspring.dao;

import org.example.exomeublesspring.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository  extends JpaRepository<CartItem, Integer> {
}
