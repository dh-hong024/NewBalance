package practice.newbalance.service.item;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import practice.newbalance.domain.item.Product;
import practice.newbalance.dto.item.ProductDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, Object>imgUpload(MultipartFile img);

    Product addProduct(ProductDto productDto);

    public List<Product> getAllProducts();
    List<Product> findProductWithProductOptionsById(Long productId);
}
