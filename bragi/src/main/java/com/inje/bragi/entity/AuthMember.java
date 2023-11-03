package com.inje.bragi.entity;

import com.inje.bragi.common.MemberType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Getter
public class AuthMember {
    @Id
    @SequenceGenerator(name = "auth_member_id_", sequenceName = "idx_member", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    private String profileImageUrl;

    @Column(name = "nickname", nullable = true, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @Builder
    public AuthMember(String name, String email, String provider, String providerId, String profileImageUrl, String nickname) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.type = MemberType.USER;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
    }

    public static AuthMember of(String name, String email, String provider, String providerId, String profileImageUrl, String nickname) {
        return AuthMember.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .provider(provider)
                .providerId(providerId)
                .profileImageUrl(profileImageUrl)
                .build();
    }

    public AuthMember update(String name, String email, String profileImageUrl) {
        this.name = name;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.type = MemberType.USER;
        return this;
    }

    public AuthMember updateProvider(String provider){
        this.provider = provider;

        return this;
    }

    public String getTypeValue(){
        return this.getType().getKey();
    }
}

