package com.nowon.cho.domain.dto;

import com.nowon.cho.domain.entity.CartEntity;
import com.nowon.cho.domain.entity.CartProductEntity;
import com.nowon.cho.domain.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartProductListDTO {
    private long no;
    private ProductListDTO product;
    private int count;

}
