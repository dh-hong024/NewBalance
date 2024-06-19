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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            imgFolder = futils.transferTo(img, true, folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(imgFolder == null) {
            return null;
        }
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("uploaded", 1);
        json.put("fileName", imgFolder);
        json.put("url", folder + "/" + imgFolder);
        return json;
    }

    @Override
    public Product addProduct(ProductDto productDto) {

        ObjectMapper mapper = new ObjectMapper();
        List<ProductOptionDto> stringObjectMap = null;

        try {
            stringObjectMap = mapper.readValue(productDto.getOption() , new TypeReference<List<ProductOptionDto>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product product = new Product();
        product.setTitle(productDto.getTitle());

        for(ProductOptionDto productOptionDto : stringObjectMap) {
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

}

