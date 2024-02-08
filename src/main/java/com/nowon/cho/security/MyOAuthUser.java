package com.nowon.cho.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

@Getter
public class MyOAuthUser implements OAuth2User {

	private Map<String, Object> attributes;
	private Collection<? extends GrantedAuthority> authorities;
	private String email;
	private String profile_image;
	private String nickName;
	
	public MyOAuthUser(OAuth2User oAuth2User, String registrationId) {
		this.authorities=oAuth2User.getAuthorities();
		if(registrationId.equals("google")) {
			ofGoogle(oAuth2User);
		}else if(registrationId.equals("naver")) {
			ofNaver(oAuth2User);
		}
			else if(registrationId.equals("kakao")) {
			ofKakao(oAuth2User);
		}
	}

	private void ofNaver(OAuth2User oAuth2User) {
		this.attributes=oAuth2User.getAttribute("response");
		//this.email=(String)attributes.get("email");
//		this.profileImg=(String)attributes.get("picture");
		this.nickName=(String)attributes.get("name");
	}
	
	private void ofKakao(OAuth2User oAuth2User) {
		this.attributes=oAuth2User.getAttribute("kakao_account");
		//email=(String)attributes.get("email");
		Map<String, String> profile= (Map<String, String>) attributes.get("profile");
		profile_image=(String)profile.get("profile_image");
		
		nickName=(String)profile.get("nickname");
	}

	private void ofGoogle(OAuth2User oAuth2User) {
		this.attributes=oAuth2User.getAttributes();
		//this.email=(String)attributes.get("email");
//		this.profileImg=(String)attributes.get("picture");
		this.nickName=(String)attributes.get("name");
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getName() { //ID에 해당하는 정보
		return nickName;
	}

}
