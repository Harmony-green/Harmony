package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartEntityRepority extends JpaRepository<CartEntity,Long> {


    Optional<CartEntity> findByMember(MemberEntity memberEntity);
}
