package com.nowon.cho.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.cho.domain.entity.MainBannerImgEntity;
import com.nowon.cho.domain.entity.MainBannerImgEntityRepository;
import com.nowon.cho.domain.entity.ProductEntity;
import com.nowon.cho.domain.entity.ProductEntityRepository;
import com.nowon.cho.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("adminServiceProcess")
public class AdminServiceProcess implements AdminService{

	private final ProductEntityRepository productRepo;
	private final MainBannerImgEntityRepository bannerRepo;
	
	@Override
	public void productlistProcess(Model model) {
		
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("list", productRepo.findAll(sort).stream()
										.map(ProductEntity::toProductListDTO)
										.collect(Collectors.toList())
				);
		
	}

	@Override
	public void mainBannerListProcess(Model model) {
		Sort sort=Sort.by(Direction.ASC, "no");
		model.addAttribute("list", bannerRepo.findAll(sort).stream()
										.map(MainBannerImgEntity::toMainBannerDTO)
										.collect(Collectors.toList())
				);
		
	}

	@Override
	public void mainBannerListDeleteProcess(long no) {
		bannerRepo.deleteById(no);
	}
	
	@Transactional
	@Override
	public void updateProcess(long no, String title, long price, int stock, String category) {
		productRepo.findById(no).orElseThrow() // 여기까지 원본 데이터
				.update(title, price, stock, category); // 내용만 수정 후 나머지는 그대로
		System.out.println("트랜잭션 적용한 메서드");
		System.out.println("연결이 유지되는 상황에서 엔터티객체가 수정되면 update가 실행됩니다");
	}
	
}
