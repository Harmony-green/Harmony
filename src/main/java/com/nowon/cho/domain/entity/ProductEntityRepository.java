package com.nowon.cho.domain.entity;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>{

	List<ProductEntity> findAllByTitleContaining(String inputValue, Sort sort);


}
