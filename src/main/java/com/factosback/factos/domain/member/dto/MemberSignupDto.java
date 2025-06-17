package com.factosback.factos.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberSignupDto {

    @Getter
    @NoArgsConstructor
    public static class MemberRequest {
        String email;
        String password;
        String nickname;
        String profile_image_url;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResponse {

    }

}
