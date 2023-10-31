package com.inje.bragi.auth;

import com.inje.bragi.entity.AuthMember;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private String name;
    private String email;
    private String provider;

    private String nickname;

    public AuthMember toMember() {
        return AuthMember.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .build();
    }

}
