package com.jvictornascimento.buynowdotcom.service.cart;

import com.jvictornascimento.buynowdotcom.model.CartItem;

public interface ICartItemService {
    void addItemCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartUd, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
    CartItem getCartItem(Long cartId, Long productId);

}
