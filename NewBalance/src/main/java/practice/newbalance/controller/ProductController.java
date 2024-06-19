package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.dto.item.ProductOptionDto;
import practice.newbalance.dto.item.ProductOptionDtoDetails;
import practice.newbalance.service.item.ProductService;

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
    public ResponseEntity<String> testOption(ProductDto productDto)  {
        productService.addProduct(productDto);

        return ResponseEntity.ok("sucess");
    }

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
