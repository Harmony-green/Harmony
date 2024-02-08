package com.nowon.cho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.service.EmailService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;

	@PostMapping("/emailConfirm")
	@ResponseBody
	public ResponseEntity<Integer> mailConfirm(@RequestBody MemberDTO memberDto) {
		int num = emailService.sendEmail(memberDto.getEmail());
		return ResponseEntity.ok(num);
	}

}