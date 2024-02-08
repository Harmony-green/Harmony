package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductEntityRepository extends JpaRepository<CartProductEntity,Long> {

    List<CartProductEntity> findAllByCart(CartEntity cart);
}
