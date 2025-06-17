package com.factosback.factos.domain.member.controller;

import com.factosback.factos.domain.component.MemberTokenStorage;
import com.factosback.factos.domain.member.dto.MemberLoginDto;
import com.factosback.factos.domain.member.dto.MemberSignupDto;
import com.factosback.factos.domain.member.service.MemberService;
import com.factosback.factos.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberTokenStorage memberTokenStorage;

    @PostMapping("/signup")
    public ApiResponse<MemberSignupDto.SignupResponse> signupMember(
            @RequestBody MemberSignupDto.MemberRequest request
    ){
        MemberSignupDto.SignupResponse response = memberService.signupMember(request).getData();

        return ApiResponse.createSuccess(response);
    }

    @PostMapping("/login")
    public ApiResponse<MemberLoginDto.LoginResponse> loginMember(
            @RequestBody MemberLoginDto.MemberRequest request
    ){
        MemberLoginDto.LoginResponse response = memberService.loginMember(request).getData();

        return ApiResponse.createSuccess(response);
    }
}
