package com.nowon.cho.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	void deleteByMember(MemberEntity member);
}