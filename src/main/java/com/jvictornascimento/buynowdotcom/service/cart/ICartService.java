package com.jvictornascimento.buynowdotcom.service.cart;

import com.jvictornascimento.buynowdotcom.model.Cart;
import com.jvictornascimento.buynowdotcom.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);
    Cart getCartByUserId(Long userId);
    void clearCart(Long cartId);
    Cart initializeNewCartForUser(User user);
    BigDecimal getToTotalPrice(Long cartId);
}
