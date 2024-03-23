package com.inje.bragi.entity;

import com.inje.bragi.common.MemberType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class AuthMember {

    @Id
    @SequenceGenerator(name = "auth_member_id_", sequenceName = "idx_member", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "provider", nullable = false)
    private String provider;


    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "nickname", nullable = true, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Builder
    public AuthMember(String name, String email, String provider, String profileImageUrl, String nickname) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.type = MemberType.USER;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
    }

    public static AuthMember of(String name, String email, String provider, String profileImageUrl, String nickname) {
        return AuthMember.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .provider(provider)
                .profileImageUrl(profileImageUrl)
                .build();
    }

    public AuthMember update(String name, String email, String profileImageUrl) {
        this.name = name;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public AuthMember updateProvider(String provider) {
        this.provider = provider;

        return this;
    }

    public String getTypeValue() {
        return this.getType().getKey();
    }
}

