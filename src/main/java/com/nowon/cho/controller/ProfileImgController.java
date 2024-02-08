package com.nowon.cho.controller;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nowon.cho.domain.dto.ProfileImgDto;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberEntityRepository;
import com.nowon.cho.domain.entity.ProfileImgEntity;
import com.nowon.cho.domain.entity.ProfileImgEntityRepository;
import com.nowon.cho.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
public class ProfileImgController {
	
	private final AmazonS3Client client;
	private final ProfileImgEntityRepository profileImgRepo;
	private final MemberEntityRepository memberRepo;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.profile-temp}")
	private String profileTempPath;
	@Value("${cloud.aws.s3.profile-path}")
	private String profileUploadPath;
	
	@ResponseBody
	@PostMapping("/profile-temp-upload")
	public Map<String, String> profileTemp(MultipartFile profileImg) {
		return FileUploadUtil.s3Upload(client, bucketName, profileTempPath, profileImg);
	}
	

	@PostMapping("/profileImg")
	public String save(ProfileImgDto profileDTO, Principal principal) {
		String uploadKey = profileUploadPath+FileUploadUtil.newFileNameByNanotime(profileDTO.getOrgName());
		FileUploadUtil.s3CopyAndDelete(client, bucketName, profileDTO.getTempKey(), uploadKey);
		String email=principal.getName();
		System.out.println(">>>>>>email :"+email);
		
		
		profileImgRepo.save(ProfileImgEntity.builder()
				.bucketKey(uploadKey)
				.orgName(profileDTO.getOrgName())
				.member(memberRepo.findByEmail(email).orElseThrow())
				.build()
				);
		
		
		return "redirect:/profile";
	}
	
	
}
