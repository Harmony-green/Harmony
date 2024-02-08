package com.nowon.cho.domain.entity;

import com.nowon.cho.domain.dto.CartProductListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "cart_product")
@Entity
public class CartProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "cart_product_no")
    private long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_no")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_cart_no")
    private GuestCartEntity guestCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private ProductEntity product;

    private int count;

    public CartProductListDTO toCartProductListDTO(){
        return CartProductListDTO.builder()
                .count(count)
                .product(product.toProductListDTO())
                .no(no)
                .build();
    }

}
