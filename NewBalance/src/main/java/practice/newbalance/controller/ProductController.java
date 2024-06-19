package practice.newbalance.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.dto.item.ProductOptionDto;
import practice.newbalance.dto.item.ProductOptionDtoDetails;
import practice.newbalance.repository.item.ProductRepository;
import practice.newbalance.service.item.ProductService;

import java.util.List;
import java.util.Map;

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


}
