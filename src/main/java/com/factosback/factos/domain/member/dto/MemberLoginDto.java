package com.factosback.factos.domain.member.dto;

import com.factosback.factos.domain.chat.model.ChatRoom;
import com.factosback.factos.domain.precedent.model.PrecedentSearch;
import com.factosback.factos.domain.term.model.TermTranslation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class MemberLoginDto {

    @Getter
    @NoArgsConstructor
    public static class MemberRequest {
        String email;
        String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponse {
        String token;
        String email;
        String nickname;
        String profileImageUrl;
        List<ChatRoom> chatRoomList;
        List<PrecedentSearch> precedentSearchList;
        List<TermTranslation> termTranslationList;
    }

}
