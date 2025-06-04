package com.jvictornascimento.buynowdotcom.controller;

import com.jvictornascimento.buynowdotcom.model.Cart;
import com.jvictornascimento.buynowdotcom.model.User;
import com.jvictornascimento.buynowdotcom.response.ApiResponse;
import com.jvictornascimento.buynowdotcom.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/carts")
public class CartController {
    private ICartService cartService;
    @GetMapping("/user/{userId}/cart")
    public ResponseEntity<ApiResponse> getUserCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(new ApiResponse("Success!", cart));
    }
    @DeleteMapping("/cart/{cartId}/clear")
    public void clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
    }
}
