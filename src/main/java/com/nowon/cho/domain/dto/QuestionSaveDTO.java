package com.nowon.cho.domain.dto;

import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.QuestionEntity;

import lombok.Setter;

@Setter
public class QuestionSaveDTO {
	
	private String title;
	private String message;
	
	
	public QuestionEntity toEntity(MemberEntity member) {
		return QuestionEntity.builder()
				.title(title).message(message)
				.member(member)//작성자 회원
				.build();
	}

}
