package practice.newbalance.controller;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Product;
import practice.newbalance.dto.item.CategoryDto;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.service.item.CategoryService;
import practice.newbalance.service.item.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {

//    private static final String UPLOAD_DIR = "C:/upload/directory"; // 이미지를 저장할 디렉토리 경로 설정

    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imgUpload(@RequestParam("upload")MultipartFile file){
        return productService.imgUpload(file);
    }

    /**
     * Product 등록
     * @param productDto
     * @return
     */
    @PostMapping("/products/addProduct")
    public ResponseEntity<String> addItem(@RequestBody ProductDto productDto) {
        try{
            productService.addProduct(productDto);
            return ResponseEntity.ok("success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @GetMapping("/prudcts/categories")
    public ResponseEntity<Map<String, Object>> categoryList(){
        Map<String, Object> response = new HashMap<>();
        List<CategoryDto> categories = categoryService.getAllCategories();
        response.put("categories", categories);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/subCategories")
    public ResponseEntity<Map<String, Object>> categoryList(@RequestParam(value = "title", required = false) String title){
        Map<String, Object> response = new HashMap<>();
        List<CategoryDto> categoryDtos = categoryService.findByCategory(title);
        response.put("categoryDtos", categoryDtos);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/detailedCategories")
    public ResponseEntity<Map<String, Object>> getDetailCategorries(
            @RequestParam(value = "parentTitle", required = false) String parentTitle,
            @RequestParam(value = "subCategoryRef", required = false) Integer subCategoryRef){
        Map<String, Object> response = new HashMap<>();
        List<CategoryDto> detailedCategories  = categoryService.findDetailedCategories(parentTitle, subCategoryRef);
        response.put("detailedCategories", detailedCategories);
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/productList")
    public ResponseEntity<List<Product>> getProductList(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/products/getProductDetails")
    public ResponseEntity<Map<String,Object>> getProductDetails(@RequestParam(value = "productId") Long productId){
        List<Product> productDetails = productService.findProductWithProductOptionsById(productId);
        List<CategoryDto> categories = categoryService.getAllCategories();
        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("productDetails", productDetails);
        return ResponseEntity.ok().body(response);
    }
}
