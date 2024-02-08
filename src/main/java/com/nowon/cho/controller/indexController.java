package com.nowon.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.s3.AmazonS3Client;
import com.nowon.cho.domain.entity.MainBannerImgEntityRepository;
import com.nowon.cho.domain.entity.ProductEntityRepository;
import com.nowon.cho.domain.entity.ProductImgEntityRepository;
import com.nowon.cho.service.AdminService;
import com.nowon.cho.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class indexController {
	
	private final ProductService service;

    @GetMapping("/sub/necklace")
    public String necklace(Model model) {
    	service.prdlistProcess(model);
    	service.prdImglistProcess(model);
        return "/sub/necklace";
    }
    @GetMapping("/sub/ring")
    public String ring(Model model) {
    	service.prdlistProcess(model);
    	service.prdImglistProcess(model);
        return "/sub/ring";
    }
    @GetMapping("/sub/best")
    public String best(Model model) {
    	service.prdlistProcess(model);
    	service.prdImglistProcess(model);
        return "/sub/best";
    }
    @GetMapping("/sub/earring")
    public String earring(Model model) {
    	service.prdlistProcess(model);
    	service.prdImglistProcess(model);
        return "/sub/earring";
    }
    @GetMapping("/sub/bracelet")
    public String bracelet(Model model) {
    	service.prdlistProcess(model);
    	service.prdImglistProcess(model);
        return "/sub/bracelet";
    }
    @GetMapping("/product-info/{pk}")
    public String yourPage(@PathVariable Long pk, Model model) {
        //model.addAttribute("pk", pk);
        service.productDetailProcess(pk, model);
        return "/sub/product-info";
    }
    @GetMapping("/result")
    public String getResultPage(@RequestParam(name = "inputValue", defaultValue = "") String inputValue, Model model) {
        // 입력된 값 모델에 추가
    	service.prdlistProcess(model, inputValue);
        model.addAttribute("inputValue", inputValue);
        return "/sub/result";
    }
    @GetMapping("/payment/{pk}")
    public String payment(@PathVariable Long pk, Model model) {
        service.productDetailProcess(pk, model);
        return "/sub/payment";
    }
    
}
