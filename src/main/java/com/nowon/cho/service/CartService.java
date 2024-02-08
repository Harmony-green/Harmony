package com.nowon.cho.service;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface CartService {

    void cartListProcess(Authentication auth, HttpSession session, Model model);
}
