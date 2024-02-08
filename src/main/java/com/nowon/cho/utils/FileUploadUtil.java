package com.nowon.cho.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.nowon.cho.domain.dto.ProductImgSaveDTO;


public class FileUploadUtil {
	
	
	public static void s3CopyAndDelete(AmazonS3Client s3Client, String bucketName, String tempKey,String uploadKey) {
		
		
		CopyObjectRequest cor=new CopyObjectRequest(bucketName, tempKey, bucketName, uploadKey);
		s3Client.copyObject(cor.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		//원본은 삭제
		s3Client.deleteObject(bucketName, tempKey);
	}

	/**
	 * 
	 * @param s3Client AmazonS3Client 객체
	 * @param bucket aws 버킷이름
	 * @param path 버킷 내부 경로
	 * @param img  MultipartFile 객체로 이미지정보
	 * @return
	 * @throws IOException 
	 */
	public static Map<String, String> s3Upload(AmazonS3Client s3Client, String bucket, String path, MultipartFile img)  {
		
		//버킷 내부의 이미지 경로
		String bucketKey=path+newFileNameByNanotime(img.getOriginalFilename());
		
		try {
			PutObjectRequest or=new PutObjectRequest(bucket, bucketKey, img.getInputStream(), objectMetadata(img));
			s3Client.putObject(or.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, String> result=new HashMap<>();
		result.put("url", s3Client.getUrl(bucket, bucketKey).toString().substring(6));
		result.put("tempKey", bucketKey);
		result.put("orgName", img.getOriginalFilename());
		return result;
	}
	
	/**
	 * 
	 * @param path : static 하위 경로 예) "/images/upload/temp"
	 * @param tempImg : MultipartFile 객체
	 * @return
	 */
	
	public static Map<String, String> classPathUpload(String path, MultipartFile tempImg) {
		ClassPathResource cpr=new ClassPathResource("static"+path);
		String newFileName=newFileName(tempImg.getOriginalFilename());
		try {
			File folder=cpr.getFile();
			if(!folder.exists())folder.createNewFile();
			
			tempImg.transferTo(new File(folder, newFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, String> result=new HashMap<>();
		result.put("url", path+newFileName);
		result.put("orgName", tempImg.getOriginalFilename());
		
		return result;
	}
	
	
	//파일 설정 정보
	private static ObjectMetadata objectMetadata(MultipartFile mf) {
		ObjectMetadata objectMetadata=new ObjectMetadata();
		objectMetadata.setContentType(mf.getContentType());//파일타입
		objectMetadata.setContentLength(mf.getSize());//파일사이즈
		return objectMetadata;
	}
	
	//파일이름 UUID를 이용하여 변경
	public static String newFileName(String orgName) {
		int idx=orgName.lastIndexOf(".");
		return UUID.randomUUID().toString() //새로운이름을 UUID로 생성
				+ orgName.substring(idx); //.확장자
	}
	
	//파일이름 nanoTime()을 이용하여 변경
	public static String newFileNameByNanotime(String orgName) {
		int idx=orgName.lastIndexOf(".");
		String rename = null;
		if(orgName!="")
			rename = orgName.substring(0, idx)+"-"+(System.nanoTime()/1000000)
					+ orgName.substring(idx); //.확장자
			System.out.println("새로만든이름"+rename);
		return rename;
	}

	
	
	

}
