package practice.newbalance.service.item;

import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.Product;
import practice.newbalance.dto.item.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, Object>imgUpload(MultipartFile img);

    Product addProduct(ProductDto productDto);
    void addCart(Long memberId, Long productId, String size, String color, int count);
    void delCart(Long cartId);
    void delAllCart(Long memberId);
    List<Cart> findCartAll(Long memberId);
    void updateCart(Long cartId, Long productId, String size, String color, int count);
}
