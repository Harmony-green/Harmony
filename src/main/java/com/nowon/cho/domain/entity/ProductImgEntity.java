package com.nowon.cho.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nowon.cho.domain.dto.ProductImgListDTO;
import com.nowon.cho.domain.dto.ProductImgSaveDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProductImg")
@Entity
public class ProductImgEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	@Column(nullable = false)
	private String bucketKey;
	@Column(nullable = false)
	private String orgNmae;
	@Column(nullable = false)
	private boolean checkImg;
	
	@ManyToOne
	private ProductEntity product;
	public ProductImgListDTO toProductImgListDTO() {
		return ProductImgListDTO.builder()
								.url("https://s3.ap-northeast-2.amazonaws.com/khc.bucket/"+bucketKey)
								.check(checkImg)
								.build();
	}
	public ProductImgListDTO toAllImgDTO() {
		return ProductImgListDTO.builder()
								.url("https://s3.ap-northeast-2.amazonaws.com/khc.bucket/"+bucketKey)
								.build();
	}
	
}
