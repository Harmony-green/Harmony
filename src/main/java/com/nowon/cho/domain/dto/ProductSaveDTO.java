package com.nowon.cho.domain.dto;

import com.nowon.cho.domain.entity.ProductEntity;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class ProductSaveDTO {

	private String name;
	private long price;
	private int stock;
	private String content;
	private String category;
	public ProductEntity toEntity() {
		return ProductEntity.builder()
				.title(name)
				.price(price)
				.stock(stock)
				.content(content)
				.category(category)
				.build();
	}
	
}
