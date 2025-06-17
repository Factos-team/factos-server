package com.factosback.factos.domain.member.repository;

import com.factosback.factos.domain.precedent.error.PrecedentErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.factosback.factos.domain.member.model.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
