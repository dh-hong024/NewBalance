package practice.newbalance.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.newbalance.domain.member.Member;

@Entity
@Setter @Getter
@Table(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;

    public static Cart createCart(Member member, Product product, int price, int count){
        Cart cart = new Cart();
        cart.setMember(member);
        cart.saveItem(cart, product, price, count);
        return cart;
    }

    public void saveItem(Cart cart, Product product, int price, int count){
        cart.setProduct(product);
        cart.setCount(count);
        cart.setPrice(price);
    }
}
