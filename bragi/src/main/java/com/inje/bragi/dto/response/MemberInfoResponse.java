package com.inje.bragi.dto.response;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record MemberInfoResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b0a-6b1c032f0e4a")
        BigInteger id,
        @Schema(description = "회원 아이디", example = "example1234")
        String account,
        @Schema(description = "회원 이름", example = "홍길동")
        String name,
        @Schema(description = "회원 나이", example = "24")
        Integer age,
        @Schema(description = "회원 타입", example = "USER")
        MemberType type,
        @Schema(description = "이미지 경로")
        String url
) {
    public static MemberInfoResponse from(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getAge(),
                member.getType(),
                member.getImage().getUrl()
        );
    }
}
