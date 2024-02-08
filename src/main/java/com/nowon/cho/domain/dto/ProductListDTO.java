package com.nowon.cho.domain.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ProductListDTO {
	
	private long no;
	private String title;
	private long price;
	private int stock;
	private String content;
	private String category;
	private String mainImgUrl;
	private List<ProductImgListDTO> imgs;
	
	
}
