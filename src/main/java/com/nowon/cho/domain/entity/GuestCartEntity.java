package com.nowon.cho.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest_cart")
@Entity
public class GuestCartEntity {

    @Id
    @Column(name = "guest_cart_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @OneToMany(mappedBy = "guestCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProductEntity> cartProduct;
}
