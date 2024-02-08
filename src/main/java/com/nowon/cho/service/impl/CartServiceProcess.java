package com.nowon.cho.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.service.CartService;

@Service
public class CartServiceProcess implements CartService{

	@Override
	public void cartListProcess(Authentication auth, HttpSession session, Model model) {
		// TODO Auto-generated method stub
		
	}

}
