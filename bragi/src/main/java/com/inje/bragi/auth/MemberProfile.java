/*
package com.inje.bragi.auth;

import com.inje.bragi.entity.AuthMember;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberProfile {
    private Long id;
    private String name;
    private String email;
    private String provider;

    private String providerId;

    private String nickname;
    private String profileImageUrl;

    public AuthMember toMember() {
        return AuthMember.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
*/
