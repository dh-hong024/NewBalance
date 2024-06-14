package practice.newbalance.service.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.repository.item.CartRepository;

import java.util.List;


@SpringBootTest
@Transactional
@ActiveProfiles("local")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @Test
    void 장바구니_추가(){
        Long memberId = 52L;
        String color = "WHITE";
        String size = "260";
        String title = "RC2002IC";
        int count = 10;

        Long cartId = productService.addCart(memberId, title, size, color, count);
        Cart cart = cartRepository.findById(cartId).get();
        Assertions.assertThat(cart.getPrice()).isEqualTo(1690000);

    }
    @Test
    void 장바구니_수정(){
        Long cartId = 52L;
        String color = "WHITE";
        String size = "290";
        String title = "RC2002IC";
        int count = 2;

        productService.updateCart(cartId, title, size, color, count);
        Cart cart = cartRepository.findById(cartId).get();

        Assertions.assertThat(cart.getProduct().getId()).isEqualTo(59L);
    }
    @Test
    void 장바구니_삭제(){
        Long cartId = 52L;

        productService.delCart(cartId);

        Assertions.assertThat(cartRepository.findById(cartId)).isEmpty();
    }

    @Test
    void 장바구니_상품_전체삭제(){
        Long memberId = 1L;

        productService.delAllCart(memberId);

        Assertions.assertThat(cartRepository.findByMemberId(memberId).size()).isEqualTo(0);
    }

    @Test
    void 장바구니_전체조회(){
        Long memberId = 1L;
        List<Cart> cartAll = productService.findCartAll(memberId);

        Assertions.assertThat(cartAll.size()).isEqualTo(2);
    }

}