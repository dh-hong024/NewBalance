package practice.newbalance.service.item;

import practice.newbalance.domain.item.Cart;

import java.util.List;

public interface ProductService {
    Long addCart(Long memberId, String title, String size, String color, int count);
    void delCart(Long cartId);
    void delAllCart(Long memberId);
    List<Cart> findCartAll(Long memberId);
    void updateCart(Long cartId, String title, String size, String color, int count);
}
