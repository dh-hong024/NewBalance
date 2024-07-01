package practice.newbalance.domain.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import practice.newbalance.domain.member.DeliveryAddress;
import practice.newbalance.domain.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private int price;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_addr_id")
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    //편의 메소드
    public void addCart(Cart cart){
        this.cartList.add(cart);
        cart.setOrder(this);
    }

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress){
        this.deliveryAddress = deliveryAddress;
        deliveryAddress.setOrder(this);
    }
}
