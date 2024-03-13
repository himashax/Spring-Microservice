package com.assignment.cartservice.service;

import com.assignment.cartservice.model.Cart;
import com.assignment.cartservice.model.CartItem;
import com.assignment.cartservice.repository.CartItemRepository;
import com.assignment.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private RestTemplate restTemplate;

    private static final String ITEM_MANAGEMENT_SERVICE_URL = "http://item-management-service";

    @Override
    public List<CartItem> getCartItems(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Cart cart = optionalCart.get();
        System.out.println("cart == " + cart.getItems());
        return cart.getItems();
    }

    @Override
    public CartItem addCartItem(Long cartId, Long itemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            ResponseEntity<CartItem> response = restTemplate.getForEntity("ITEM_MANAGEMENT_SERVICE_URL/items" + itemId, CartItem.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                CartItem cartItem = response.getBody();
                System.out.println("Fetched item: " + cartItem.getName());

                cartItem.setCart(cart);


                cartItemRepository.save(cartItem);

                return cartItem;
            } else {
                throw new RuntimeException("Failed to fetch item details");
            }
        } else {
            throw new RuntimeException("Cart not found");
        }
    }
}
