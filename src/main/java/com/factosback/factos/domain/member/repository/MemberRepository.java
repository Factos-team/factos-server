package com.factosback.factos.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.member.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
