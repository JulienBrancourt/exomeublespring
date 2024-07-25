package org.example.exomeublesspring.controller;

import org.example.exomeublesspring.entity.CartItem;
import org.example.exomeublesspring.service.CartItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartItemController {
    private CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cartitems")
    public String cartItems(Model model) {
        model.addAttribute("cartItems", cartItemService.getCartItems());
        return "panier";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("id") int id, Model model) {
        model.addAttribute("cartItem", new CartItem());
        System.out.println("on passe par le get add to cart");
        return "home";
    }

    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute("cartItem") CartItem cartItem,@RequestParam("id") int id ,Model model) {
        cartItemService.addFurniture(new CartItem(), id);
        model.addAttribute("cartItems", cartItemService.getCartItems());
        System.out.println("on passe par le post add to cart");
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        cartItemService.removeFurniture(id);
        return "redirect:/cartitems";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        cartItemService.removeAllCartItem();
        return "redirect:/cartitems";
    }

}
