package org.example.exomeublesspring.service;

import org.example.exomeublesspring.dao.CartItemRepository;
import org.example.exomeublesspring.dao.FurnitureRepository;
import org.example.exomeublesspring.entity.CartItem;
import org.example.exomeublesspring.entity.Furniture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final FurnitureService furnitureService;
//    private final FurnitureRepository furnitureRepository;

    public CartItemService(CartItemRepository cartItemRepository,FurnitureRepository furnitureRepository, FurnitureService furnitureService) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureService = furnitureService;
//        this.furnitureRepository = furnitureRepository;
    }

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public void clearCartItems() {
        cartItemRepository.deleteAll();
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void addFurniture(CartItem cartItem, int id) {
    Furniture furniture = furnitureService.findById(id);

        boolean alreadyInCart = false;
        for (CartItem c : getCartItems()) {
            if (c.getFurniture().getId() == furniture.getId()) {
                alreadyInCart = true;
                c.setQuantity(c.getQuantity() + 1);
                cartItemRepository.save(c);
                break;
            }
        }
        if (!alreadyInCart) {
            CartItem cartItemToAdd = new CartItem();
            cartItemToAdd.setFurniture(furniture);
            cartItemToAdd.setQuantity(1);
            cartItemRepository.save(cartItemToAdd);
        }
        furniture.setStock(furniture.getStock()-1);
        furnitureService.save(furniture);
    }

    public void removeFurniture(int id) {
        CartItem cartItemToDelete = cartItemRepository.findById(id).orElse(null);
        Furniture furniture = furnitureService.findById(cartItemToDelete.getFurniture().getId());
        furniture.setStock(furniture.getStock() + cartItemToDelete.getQuantity());
        furnitureService.save(furniture);
        cartItemRepository.delete(cartItemToDelete);
    }

    public void removeAllCartItem() {
        List<CartItem> cartItems = getCartItems();
        for (CartItem c : cartItems) {
            Furniture furniture = furnitureService.findById(c.getFurniture().getId());
            furniture.setStock(furniture.getStock() + c.getQuantity());
            furnitureService.save(furniture);
        }
        cartItemRepository.deleteAll();
    }

}
