package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nowon.cho.domain.dto.MainBannerImgDTO;

public interface MainBannerImgEntityRepository extends JpaRepository<MainBannerImgEntity, Long>{

	void save(MainBannerImgDTO bannerEntity);

}
