package com.factosback.factos.domain.member.service;

import com.factosback.factos.domain.chat.error.ChatErrorCode;
import com.factosback.factos.domain.chat.model.ChatRoom;
import com.factosback.factos.domain.component.MemberTokenStorage;
import com.factosback.factos.domain.member.dto.MemberLoginDto;
import com.factosback.factos.domain.member.dto.MemberSignupDto;
import com.factosback.factos.domain.member.model.Member;
import com.factosback.factos.domain.member.model.MemberStatus;
import com.factosback.factos.domain.member.repository.MemberRepository;
import com.factosback.factos.domain.precedent.error.PrecedentErrorCode;
import com.factosback.factos.domain.precedent.model.PrecedentSearch;
import com.factosback.factos.domain.term.model.TermTranslation;
import com.factosback.factos.global.error.code.ErrorCode;
import com.factosback.factos.global.error.exception.RestApiException;
import com.factosback.factos.global.response.ApiResponse;
import com.factosback.factos.global.security.utils.JwtUtil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.color.ICC_Profile;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;
    private MemberTokenStorage memberTokenStorage;

    @Transactional
    public ApiResponse<MemberSignupDto.SignupResponse> signupMember(
            MemberSignupDto.MemberRequest request
    ){

        Member member = new Member(
                request.getEmail(),
                BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()),
                request.getNickname(),
                request.getProfile_image_url()
        );

        memberRepository.save(member);

        MemberSignupDto.SignupResponse response = new MemberSignupDto.SignupResponse();

        return ApiResponse.createSuccess(response);
    }

    public ApiResponse<MemberLoginDto.LoginResponse> loginMember(
            MemberLoginDto.MemberRequest request
    ) {

        Member member = memberRepository.findByEmail(request.getEmail());

        if(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()).equals(member.getPassword())){
            String token = JwtUtil.generateToken(member.getEmail());
            memberTokenStorage.storeToken(token, member.getId());

            MemberLoginDto.LoginResponse response
                    = new MemberLoginDto.LoginResponse(
                            token, member.getEmail(), member.getNickname(),
                        member.getProfileImageUrl(), member.getChatRoomList(), member.getPrecedentSearchList()
                        , member.getTermTranslationList()
                    );

            return ApiResponse.createSuccess(response);
        }else{ // Wrong Password
            ErrorCode errorCode = new ErrorCode() {
                @Override
                public String name() {
                    return "";
                }

                @Override
                public HttpStatus getHttpStatus() {
                    return HttpStatus.UNAUTHORIZED;
                }

                @Override
                public String getMessage() {
                    return "Wrong Password!";
                }
            };
            return (ApiResponse<MemberLoginDto.LoginResponse>) ApiResponse.createFail(errorCode);
        }
    }
}
