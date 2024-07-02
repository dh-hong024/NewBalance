package practice.newbalance.repository.item.query;

import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.dto.item.ProductOptionDto;

import java.util.List;

public interface CustomProductRepository {
    public List<ProductDto> getProductDetail(Long productId);

    List<ProductOptionDto> getProductOption(Long productId);
}
