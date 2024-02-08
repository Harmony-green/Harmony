package com.nowon.cho.controller;

import com.nowon.cho.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class CartController {

	private final CartService service;

	@GetMapping("/cart")
	public String cart(Authentication auth, HttpSession session,  Model model) {
		System.out.println(">>>>:"+auth);
		//String email = principal.getName();
		//String email=null;
		service.cartListProcess(auth,session,model);



		return "cart/cart-normal";
	}
}
