package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.SignInRequest;
import com.inje.bragi.dto.request.SignUpRequest;
import com.inje.bragi.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 가입 및 로그인")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class SignController {
    private final SignService signService;


    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ApiResponse signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(signService.registerMember(request));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ApiResponse signIn(@RequestBody SignInRequest request) {
        return ApiResponse.success(signService.signIn(request));

    }
}
