package com.nowon.cho.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.Data;

@Data
public class MemberDTO {

	private String email;
	private String pass;
	private String name;
	private String phone;
	
	public MemberEntity getDTO(PasswordEncoder pe) {
		return MemberEntity.builder()
				.email(email)
				.pass(pe.encode(pass))
				.name(name)
				.phone(phone)
				.build();
	}
}
