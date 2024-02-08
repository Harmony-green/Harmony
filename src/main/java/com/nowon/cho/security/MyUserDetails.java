package com.nowon.cho.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.nowon.cho.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	
	public MyUserDetails(MemberEntity member, String password, Collection<? extends GrantedAuthority> authorities) {
		super(member.getEmail(), password, authorities);
		this.name=member.getName();
		this.phone=member.getPhone();
	}
	
	public boolean isAdmin() {
        return getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(MyRole.ADMIN.getRoleName()));
    }

}
