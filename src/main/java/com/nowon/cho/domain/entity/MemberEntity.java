package com.nowon.cho.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HermonyMember")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String pass;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String phone;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "HarmonyRole")
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MyRole> roles=new HashSet<>();
	public MemberEntity addRole(MyRole role) {
		roles.add(role);
		return this;
	}
	
	public MemberEntity getDTO(MemberDTO memberDTO) {
		this.email = memberDTO.getEmail();
		this.pass = memberDTO.getPass();
		this.name = memberDTO.getName();
		this.phone = memberDTO.getPhone();
		return this;
	}
	
	@OneToOne(mappedBy = "member")
    private ProfileImgEntity profileImg;
}