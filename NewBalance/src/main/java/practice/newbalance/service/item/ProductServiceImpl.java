package practice.newbalance.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import practice.newbalance.common.ErrorCode;
import practice.newbalance.common.exception.CustomException;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.member.Member;
import practice.newbalance.repository.MemberRepository;
import practice.newbalance.repository.item.CartRepository;
import practice.newbalance.repository.item.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    //장바구니 상품 추가
    @Override
    public Long addCart(Long memberId, String title, String size, String color, int count) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)
        );

        Product product = productRepository.findProductByOption(size, color, title);

        Cart cart = Cart.createCart(member, product, product.getPrice() * count, count);
        cartRepository.save(cart);

        return cart.getId();

    }

    //장바구니 상품 삭제
    @Override
    public void delCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    //장바구니 모든 상품 삭제
    @Override
    public void delAllCart(Long memberId) {
        cartRepository.deleteByMemberId(memberId);
    }

    //장바구니 조회
    @Override
    public List<Cart> findCartAll(Long memberId) {
        return cartRepository.findByMemberId(memberId);
    }

    //장바구니 상품 수정
    @Override
    public void updateCart(Long cartId, String title, String size, String color, int count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)
        );
        Product productByOption = productRepository.findProductByOption(size, color, title);
        cart.saveItem(cart, productByOption, productByOption.getPrice() * count, count);

        cartRepository.save(cart);
    }
}

