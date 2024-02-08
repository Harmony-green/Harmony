package com.nowon.cho.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.nowon.cho.domain.dto.MemberDTO;

public interface MemberService {

	void save(MemberDTO memberDTO);

	ResponseEntity<String> changePassword(String currentpass, String newpass, String newpassagain, Principal principal);

	ResponseEntity<String> deleteUser(String password, Principal principal);
	
	
}
