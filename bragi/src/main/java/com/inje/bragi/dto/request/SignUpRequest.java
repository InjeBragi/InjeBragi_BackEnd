package com.inje.bragi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpRequest (
        @Schema(description = "회원 아이디", example = "test1234")
        String account,

        @Schema(description = "회원 비밀번호", example = "1234")
        String password,

        @Schema(description = "회원 이름", example = "고동민")
        String name,

        @Schema(description = "회원 나이", example = "24")
        Integer age
){}
