package practice.newbalance.service.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;
import practice.newbalance.domain.member.Member;
import practice.newbalance.repository.MemberRepository;
import practice.newbalance.repository.item.CartRepository;
import practice.newbalance.repository.item.ProductOptionRepository;
import practice.newbalance.repository.item.ProductRepository;

import java.util.List;


@SpringBootTest
@Transactional
@ActiveProfiles("local")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductOptionRepository optionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void 장바구니_추가(){

        Product product = new Product();
        ProductOption option = new ProductOption();
        ProductOption option2 = new ProductOption();
        ProductOption option3 = new ProductOption();

        //상품 생성
        product.setTitle("뉴발신발");
        product.setContent("뉴발내용");
        product.setPrice(129000);
        product.setCode("ABCDEFG");
        product.setContry("KOREA");
        product.setFeatures("면 100%");

        //옵션1 생성
        option.setQuantity(10);
        option.setSize("220");
        option.setColor("RED");
        //옵션2 생성
        option2.setQuantity(10);
        option2.setSize("220");
        option2.setColor("RED");
        //옵션3 생성
        option3.setQuantity(10);
        option3.setSize("220");
        option3.setColor("RED");

        product.addOption(option);
        product.addOption(option2);
        product.addOption(option3);

        em.persist(product);
        em.flush();
        em.clear();

        Long memberId = 1L;
        Long productId = product.getId();
        String size = "230";
        String color = "RED";
        int count = 2;

        productService.addCart(memberId, productId, size, color, count);

        //
        Assertions.assertThat(cartRepository.findAll().size()).isEqualTo(1);


    }
    @Test
    @Transactional
    void 장바구니_수정(){
        String size = "220";
        String color = "#20dfa6";
        int count = 5;
        Long memberId = 1L;
        Long productId = 2L;

        Member member = memberRepository.findById(memberId).get();
        Product product = productRepository.findProductById(productId).get();

        Cart cart = Cart.createCart(member, product, product.getProductOptions().get(0), 129000, 1);
        cartRepository.save(cart);

        productService.updateCartOption(cart.getId(), size, color);
        productService.updateCartCount(cart.getId(), count);

        Assertions.assertThat(cartRepository.findById(cart.getId()).get().getCount()).isEqualTo(5);
        Assertions.assertThat(cartRepository.findById(cart.getId()).get().getProductOption().getColor()).isEqualTo("#20dfa6");
        Assertions.assertThat(cartRepository.findById(cart.getId()).get().getProductOption().getSize()).isEqualTo("220");
        Assertions.assertThat(cartRepository.findById(cart.getId()).get().getPrice()).isEqualTo(645000);


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