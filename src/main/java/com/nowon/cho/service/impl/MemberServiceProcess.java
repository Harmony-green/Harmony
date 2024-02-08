package com.nowon.cho.service.impl;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.cho.domain.dto.MemberDTO;
import com.nowon.cho.domain.entity.MemberEntity;
import com.nowon.cho.domain.entity.MemberEntityRepository;
import com.nowon.cho.domain.entity.ProfileImgEntity;
import com.nowon.cho.domain.entity.ProfileImgEntityRepository;
import com.nowon.cho.domain.entity.QuestionEntity;
import com.nowon.cho.domain.entity.QuestionRepository;
import com.nowon.cho.security.MyRole;
import com.nowon.cho.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceProcess implements MemberService {

	@Autowired
	MemberEntityRepository memRepo;

	@Autowired
	PasswordEncoder pe;

	@Autowired
	ProfileImgEntityRepository profileImgRepo;

	@Autowired
	QuestionRepository questionRepo;

	@Override
	@Transactional
	public void save(MemberDTO memberdto) {
		memRepo.save(memberdto.getDTO(pe).addRole(MyRole.USER));
	}

	@Override
	@Transactional
	public ResponseEntity<String> changePassword(String currentpass, String newpass, String newpassagain,
			Principal principal) {
		String userEmail = principal.getName();
		Optional<MemberEntity> optionalMember = memRepo.findByEmail(userEmail);

		if (optionalMember.isPresent()) {
			MemberEntity member = optionalMember.get();

			if (pe.matches(currentpass, member.getPass())) {
				if (newpass.equals(newpassagain)) {
					member.setPass(pe.encode(newpass));
					memRepo.save(member);
					return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
				} else {
					return ResponseEntity.badRequest().body("새 패스워드가 일치하지 않습니다.");
				}
			} else {
				return ResponseEntity.badRequest().body("현재 패스워드가 일치하지 않습니다.");
			}
		} else {
			return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
		}
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteUser(String password, Principal principal) {
	    String userEmail = principal.getName();
	    Optional<MemberEntity> optionalMember = memRepo.findByEmail(userEmail);

	    if (optionalMember.isPresent()) {
	        MemberEntity member = optionalMember.get();

	        // 현재 비밀번호 일치 여부 확인
	        if (pe.matches(password, member.getPass())) {
	            ProfileImgEntity profileImg = member.getProfileImg();
//	            profileImgRepo.delete(profileImg);
//	            questionRepo.deleteByMember(member);

	            // 로그아웃 처리
	            SecurityContextHolder.clearContext();

	            // hermony_member 테이블에서 회원 행 삭제
	            memRepo.delete(member);

	            return ResponseEntity.ok("회원 탈퇴가 성공적으로 처리되었습니다.");
	        } else {
	            return ResponseEntity.badRequest().body("현재 비밀번호가 일치하지 않습니다.");
	        }
	    } else {
	        return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
	    }
	}

}
