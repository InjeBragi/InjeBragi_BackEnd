package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.service.SpotifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Spotify API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    private final SpotifyService spotifyService;

    @Operation(summary = "곡 정보 검색")
    @GetMapping("/search")
    public ApiResponse searchMusic(@RequestParam String keyword) {
        return ApiResponse.success(spotifyService.search(keyword));
    }

    @Operation(summary = "Spotify 토큰")
    @GetMapping("/get-token")
    public ApiResponse getToken(){
        return ApiResponse.success(spotifyService.getAccessToken());
    }
}
