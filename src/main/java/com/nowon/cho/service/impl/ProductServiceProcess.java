package com.nowon.cho.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.entity.MainBannerImgEntity;
import com.nowon.cho.domain.entity.MainBannerImgEntityRepository;
import com.nowon.cho.domain.entity.ProductEntity;
import com.nowon.cho.domain.entity.ProductEntityRepository;
import com.nowon.cho.domain.entity.ProductImgEntity;
import com.nowon.cho.domain.entity.ProductImgEntityRepository;
import com.nowon.cho.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("productServiceProcess")
public class ProductServiceProcess implements ProductService{

	private final ProductEntityRepository productRepo;
	private final ProductImgEntityRepository proImgRepo;
	
	@Override
	public void prdlistProcess(Model model, String inputValue) {
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("list", productRepo.findAllByTitleContaining(inputValue,sort).stream()
										.map(ProductEntity::toProductListDTO)
										.collect(Collectors.toList())
				);
		
	}
	
	@Override
	public void prdlistProcess(Model model) {
		
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("list", productRepo.findAll(sort).stream()
										.map(ProductEntity::toProductListDTO)
										.collect(Collectors.toList())
				);
		
	}
	@Override
	public void prdImglistProcess(Model model) {
		
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("imglist", proImgRepo.findAll(sort).stream()
										.map(ProductImgEntity::toProductImgListDTO)
										.collect(Collectors.toList())
				);
		
	}
	
	
	@Override
	public void productDetailProcess(Long pk,Model model) {
		model.addAttribute("dto", productRepo.findById(pk)
				.map(ProductEntity::toProductListDTO)
				.orElseThrow());
		
	}
	@Override
	public void productSearchProcess(Model model) {
		
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("dto", productRepo.findAll(sort).stream()
				.map(ProductEntity::toProductListDTO))
				;
		
	}
	

}
