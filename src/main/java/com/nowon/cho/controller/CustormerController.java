package com.nowon.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nowon.cho.domain.dto.QuestionSaveDTO;
import com.nowon.cho.service.QuestionService;

@Controller
public class CustormerController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/cs")
    public String csPage() {
        // 해당 경로에 대한 로직 처리
        return "customer/cs";
    }

    @GetMapping("/csButton")
    public String csButton() {
        // 해당 경로에 대한 로직 처리
        return "customer/cs";
    }

	@GetMapping("/faq")
	public String faq() {
		return "customer/faq";
	}

	@GetMapping("/info")
	public String info() {
		return "customer/info";
	}

	@GetMapping("/qna")
	public String qna() {
		return "customer/qna";
	}

	@PostMapping("/qna/register")
	public String registerQuestion(QuestionSaveDTO dto, Authentication auth) {

		return questionService.saveQuestion(dto, auth);
	}

	@GetMapping("/infoadmin")
	public String infoAdmin() {
		return "customer/infoadmin"; // 해당하는 Thymeleaf 템플릿 경로로 수정
	}

}