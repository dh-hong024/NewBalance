package practice.newbalance.service.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.dto.item.ProductOptionDto;
import practice.newbalance.dto.item.ProductOptionDtoDetails;
import practice.newbalance.repository.item.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final FileUtils futils;

    private final ProductRepository productRepository;

    @Override
    public Map<String, Object> imgUpload(MultipartFile img) {
         String folder = "/res/img/product/imgFolder";
         String imgFolder = null;

        try {
//            imgFolder = futils.transferTo(img, true, folder);
            imgFolder = futils.transferTo(img, folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(imgFolder == null) {
            return null;
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("uploaded", 1);
        json.put("fileName", imgFolder);
//        json.put("url", "/upload" + folder.replace("/","\\") + "\\" + imgFolder);
//        json.put("url", "/upload" + folder + "/" + imgFolder);
        json.put("url", folder + "/" + imgFolder);
        return json;
    }

    @Override
    public Product addProduct(ProductDto productDto) {

        ObjectMapper mapper = new ObjectMapper();
        List<ProductOptionDto> productOptionDtoList = null;

        try {
            productOptionDtoList = mapper.readValue(productDto.getOption() , new TypeReference<List<ProductOptionDto>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setContry(productDto.getContry());
        product.setFeatures(productDto.getFeatures());
        product.setManufactureDate(productDto.getManufactureDate());
        product.setMaterial(productDto.getMaterial());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setContent(productDto.getContent());
        product.setImageUrls(productDto.getImageUrls());
        product.setCategory(productDto.getCategory());

        for(ProductOptionDto productOptionDto : productOptionDtoList) {
            String color = productOptionDto.getColor();

            for(ProductOptionDtoDetails optionDtoDetails : productOptionDto.getProductOptionDtoDetailsList()){
                ProductOption productOption = new ProductOption();

                productOption.setColor(color);
                productOption.setSize(optionDtoDetails.getSizeValue());
                productOption.setQuantity(optionDtoDetails.getQuantity());

                product.addOption(productOption);

            }
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }


    @Override
    public List<Product> findProductWithProductOptionsById(Long productId) {
        return productRepository.findProductWithProductOptionsById(productId);
    }
}

