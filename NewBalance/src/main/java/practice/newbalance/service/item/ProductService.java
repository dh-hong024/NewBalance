package practice.newbalance.service.item;

import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Product;
import practice.newbalance.dto.item.ProductDto;

import java.util.Map;

public interface ProductService {

    Map<String, Object>imgUpload(MultipartFile img);

    Product addProduct(ProductDto productDto);
}
