package practice.newbalance.service.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import practice.newbalance.common.ErrorCode;
import practice.newbalance.common.exception.CustomException;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.item.ProductDto;
import practice.newbalance.dto.item.ProductOptionDto;
import practice.newbalance.dto.item.ProductOptionDtoDetails;
import practice.newbalance.repository.MemberRepository;
import practice.newbalance.repository.item.CartRepository;
import practice.newbalance.repository.item.ProductOptionRepository;
import practice.newbalance.repository.item.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final FileUtils futils;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository optionRepository;

    @Transactional
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

    @Transactional
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

    //장바구니 상품 추가
    @Transactional
    @Override
    public void addCart(Long memberId, Long productId, String size, String color, int count) {
        //멤버 조회
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)
        );

        //상품 조회
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)
        );

        //option 조회 및 cart 생성
        product.getProductOptions().stream()
                .filter(p -> p.getColor().equals(color) && p.getSize().equals(size))
                .forEach(productOption -> {
                    Cart cart = Cart.createCart(member, product, productOption, product.getPrice() * count, count);
                    cartRepository.save(cart);
                });
    }

    //장바구니 상품 삭제
    @Transactional
    @Override
    public void delCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    //장바구니 모든 상품 삭제
    @Transactional
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
    @Transactional
    @Override
    public void updateCart(Long cartId, Long productId, String size, String color, int count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)
        );

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.NOT_EXISTED_DATA)

        );
        product.getProductOptions().stream()
                .filter(p -> p.getColor().equals(color) && p.getSize().equals(size))
                .forEach(
                        productOption -> cart.saveItem(cart, product, productOption, product.getPrice() * count, count)
                );

    }
}

