package com.nowon.cho.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainBannerImgDTO {
	
	private long no;
	private String tempKey;
	private long orderNumber;
	private String title;
	private String sub;
	private String UrlLink;
	private String orgName;
	private String url;
}
