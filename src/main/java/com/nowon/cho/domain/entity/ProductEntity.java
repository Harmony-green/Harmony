package com.nowon.cho.domain.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nowon.cho.domain.dto.ProductListDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private long price;
	@Column(nullable = false)
	private int stock;
	@Column(nullable = false, columnDefinition = "text")
	private String content;
	@Column(nullable = false)
	private String category;
	
	@OneToMany(mappedBy = "product")
	private List<ProductImgEntity> imgs;
	
	public ProductListDTO toProductListDTO() {
		
		ProductImgEntity mainImg=imgs.stream()
				.filter(img -> img.isCheckImg()==true)
				.findFirst()
				.get();
				
		//List<ProductImgSaveDTO> allImgs=;
		return ProductListDTO.builder()
							.no(no)
							.title(title)
							.price(price)
							.stock(stock)
							.content(content)
							.category(category)
							.imgs(imgs.stream()
									.map(ProductImgEntity::toProductImgListDTO)
									.collect(Collectors.toList()))
							.mainImgUrl("https://s3.ap-northeast-2.amazonaws.com/khc.bucket/"+mainImg.getBucketKey())
				.build();
		
	}
	public ProductEntity update(String title, long price, int stock, String category) {
		this.title=title;
		this.price=price;
		this.stock=stock;
		this.category=category;
		return this;
	}
	
}
