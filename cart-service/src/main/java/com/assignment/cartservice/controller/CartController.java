package com.assignment.cartservice.controller;

import com.assignment.cartservice.model.CartItem;
import com.assignment.cartservice.service.CartService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{id}")
    public String addItemToCart(@PathVariable("id") Long itemId) {
        System.out.println("called the api");
        cartService.addCartItem(2L, itemId);
        return "Item added successfully";
    }
    @GetMapping("/{id}")
    public List<CartItem> viewCartItems(@PathVariable("id") Long cartId) {
        return cartService.getCartItems(cartId);
    }

    @GetMapping("")
    public String message() {
        return "Cart service";
    }
}
