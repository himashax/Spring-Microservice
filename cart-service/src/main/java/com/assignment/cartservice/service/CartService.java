package com.assignment.cartservice.service;

import com.assignment.cartservice.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItems(Long cartId);
    CartItem addCartItem(Long cartId, Long itemId);


}
