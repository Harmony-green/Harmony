package com.nowon.cho.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "cart")
@Entity
public class CartEntity {
    @Id
    @Column(name = "cart_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private MemberEntity member;
}
