package com.inje.bragi.dto.response;

import com.inje.bragi.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record SignUpResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        BigInteger id,

        @Schema(description = "회원 아이디", example = "example1234")
        String account,

        @Schema(description = "회원 이름", example = "홍길동")
        String name,

        @Schema(description = "회원 나이", example = "24")
        Integer age

) {
    public static SignUpResponse from(Member member) {
        return new SignUpResponse(
                member.getId(),
                member.getAccount(),
                member.getName(),
                member.getAge()
        );
    }
}
