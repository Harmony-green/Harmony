package com.nowon.cho.service;

import org.springframework.ui.Model;

public interface ProductService {
	
	void prdlistProcess(Model model);
	void prdImglistProcess(Model model);
	void productDetailProcess(Long pk, Model model);
	void productSearchProcess(Model model);
	void prdlistProcess(Model model, String inputValue);

}
