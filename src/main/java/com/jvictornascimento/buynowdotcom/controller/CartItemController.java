package com.jvictornascimento.buynowdotcom.controller;

import com.jvictornascimento.buynowdotcom.model.Cart;
import com.jvictornascimento.buynowdotcom.model.User;
import com.jvictornascimento.buynowdotcom.response.ApiResponse;
import com.jvictornascimento.buynowdotcom.service.cart.ICartItemService;
import com.jvictornascimento.buynowdotcom.service.cart.ICartService;
import com.jvictornascimento.buynowdotcom.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final IUserService userService;
    private final ICartService cartService;
    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@PathVariable Long userId,
                                                     @RequestParam Long productId,
                                                     @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        Cart userCart = cartService.initializeNewCartForUser(user);
        cartItemService.addItemCart(userCart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item added successfully", null));
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,
                                                          @PathVariable Long itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Item removed successfully!", null));
    }
    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartId,
                                                      @PathVariable Long itemId,
                                                      @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId,itemId,quantity);
        return ResponseEntity.ok(new ApiResponse("Item updated successfully!",null));
    }
}
