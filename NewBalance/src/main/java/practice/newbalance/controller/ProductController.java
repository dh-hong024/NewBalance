package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.service.item.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import practice.newbalance.config.security.CustomUserDetail;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.dto.item.CartDto;


@Controller
@RequiredArgsConstructor
public class ProductController {

//    private static final String UPLOAD_DIR = "C:/upload/directory"; // 이미지를 저장할 디렉토리 경로 설정

    private final ProductService productService;

    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imgUpload(@RequestParam("upload")MultipartFile file){
        return productService.imgUpload(file);
    }


    @PostMapping("/product/addOptions")
    @ResponseBody
    public ResponseEntity<String> addProduct(ProductDto productDto)  {
        productService.addProduct(productDto);

        return ResponseEntity.ok("sucess");
    }


    @GetMapping("/my/cart")
    public String cartHome(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            Model model
    ){
        List<Cart> cartAll = productService.findCartAll(customUserDetail.getMember().getId());
        List<String> countList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            countList.add(String.valueOf(i+1));
        }
        model.addAttribute("cartList", cartAll);
        model.addAttribute("countList", countList);
        return "item/cart";
    }

    @PostMapping("/my/cart")
    public ResponseEntity<String> addCart(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            CartDto dto
    ){
        productService.addCart(
                customUserDetail.getMember().getId(), dto.getProductId(),
                dto.getSize(), dto.getColor(), dto.getCount()
        );
        return ResponseEntity.ok("success");
    }

    @PutMapping("/my/cart/option/{cartId}")
    public ResponseEntity<String> updateCart(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable("cartId") Long cartId,
            CartDto dto
    ){
        productService.updateCart(cartId, dto.getProductId(), dto.getSize(), dto.getColor(), dto.getCount());
        return ResponseEntity.ok("success");
    }

    @PutMapping("/my/cart/count/{cartId}")
    public ResponseEntity<String> updateCartCount(@PathVariable("cartId") Long cartId, @RequestParam("count") int count){
        productService.updateCartCount(cartId, count);
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
