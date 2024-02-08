package com.nowon.cho.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberEntityRepository;

public class HUserDetailsService implements UserDetailsService{

	@Autowired
	MemberEntityRepository memRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberEntity member = memRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
		Set<SimpleGrantedAuthority> grantedAuthority = member.getRoles().stream()
				.map(myRole -> new SimpleGrantedAuthority(myRole.name())).collect(Collectors.toSet());
		
		return new MyUserDetails(member, member.getPass(), grantedAuthority);
	}
}
