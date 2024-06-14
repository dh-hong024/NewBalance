package practice.newbalance.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.config.security.CustomUserDetail;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.dto.item.CartDto;
import practice.newbalance.service.item.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/my/cart")
    public String cartHome(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            Model model
    ){
        List<Cart> cartAll = productService.findCartAll(customUserDetail.getMember().getId());
        model.addAttribute("cartList", cartAll);
        return "item/cart";
    }

    @PostMapping("/my/cart")
    public ResponseEntity<String> addCart(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            CartDto dto
    ){
        productService.addCart(
                customUserDetail.getMember().getId(), dto.getTitle(),
                dto.getSize(), dto.getColor(), dto.getCount()
        );
        return ResponseEntity.ok("success");
    }

    @PutMapping("/my/cart/{cartId}")
    public ResponseEntity<String> updateCart(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable("cartId") Long cartId,
            CartDto dto
    ){
        productService.updateCart(cartId, dto.getTitle(), dto.getSize(), dto.getColor(), dto.getCount());
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/my/cart/{cartId}")
    public ResponseEntity<String> delCart(@PathVariable("cartId") Long cartId){
        productService.delCart(cartId);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/my/cart")
    public ResponseEntity<String> delAllCart(@AuthenticationPrincipal CustomUserDetail customUserDetail){
        productService.delAllCart(customUserDetail.getMember().getId());
        return ResponseEntity.ok("success");
    }
}
