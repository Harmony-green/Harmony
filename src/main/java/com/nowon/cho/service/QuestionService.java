package com.nowon.cho.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.dto.QuestionSaveDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberEntityRepository;
import com.nowon.cho.domain.entity.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
    private final QuestionRepository questionRepository;
    private final MemberEntityRepository memberEntityRepository;

	

	public String saveQuestion(QuestionSaveDTO dto, Authentication auth) {
		
		if(auth==null) {
			System.out.println("세센이 만료된 상황");
			return "redirect:/users/login?sessionError";
		}
		
		//현재 로그인 유저인경우만 
		String email=auth.getName();
		MemberEntity member=memberEntityRepository.findByEmail(email).orElseThrow();
		
		questionRepository.save(dto.toEntity(member));
		
		return "redirect:/cs";
		
	}
}