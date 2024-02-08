package com.nowon.cho.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberEntityRepository;
import com.nowon.cho.security.MyRole;
import com.nowon.cho.service.MemberService;

@Controller
public class UserController {
	
	@Autowired
	MemberEntityRepository repo;
	@Autowired
	MemberService memservice;
	@Autowired
	PasswordEncoder pe;

	@GetMapping("/users/login")
	public String login() {
		return "users/login";
	}
	
	@GetMapping("/users/signup")
	public String signup() {
		return "users/signup";
	}
	
	@GetMapping("/users/signupagree")
	public String signupagree() {
		return "users/signupagree";
	}
	
	@PostMapping("/users/signup")
	public String signup(MemberDTO dto) {
		memservice.save(dto);
		return "redirect:/";
	}
	
	@PutMapping("/users/{no}")
	@ResponseBody
	public ResponseEntity<String> changePassword(@RequestParam String currentpass,
			@RequestParam String newpass, @RequestParam String newpassagain, Principal principal) {
		return memservice.changePassword(currentpass, newpass, newpassagain, principal);
	}

	@GetMapping("/users/delete")
	public String delete() {
		return "users/delete";
	}
	
	@PostMapping("/users/delete")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@RequestParam String password, Principal principal) {
	    return memservice.deleteUser(password, principal);
	}
}