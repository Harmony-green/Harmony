package com.nowon.cho.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductImgListDTO {

	//private String tempKey;
	//private String orgName;
	//private boolean checkImg;
	private String url;
	private boolean check;

}
