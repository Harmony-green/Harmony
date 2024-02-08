package com.nowon.cho.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyOauth2userService extends DefaultOAuth2UserService{

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		//registrationId : 현재 로그인진행중인 서비스를 구분하는 코드
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		log.debug(">>>>>registrationId : "+registrationId);
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.debug(">>>>>name : "+oAuth2User.getName());
		
		oAuth2User.getAuthorities().forEach(role->{
			log.debug(">>>>>role : "+role);
		});
		
		oAuth2User.getAttributes().forEach((key,value)->{
			log.debug(">>>>>key : "+key + ", value : " + value);
		});
		return new MyOAuthUser(oAuth2User, registrationId);
	}

}
