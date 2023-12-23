package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.TrackCreateRequest;
import com.inje.bragi.service.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;

@Tag(name = "음원 업로드 관련 api")
@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @Operation(summary = "음원 업로드")
    @PostMapping(name = "/write", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse boardWrite(@RequestBody TrackCreateRequest req, @AuthenticationPrincipal User user) throws IOException {
        return ApiResponse.success(trackService.writeTrack(req, new BigInteger(user.getUsername())));
    }

    @Operation(summary = "전체 음원 조회")
    @GetMapping("/posts")
    public ApiResponse getPosts() {
        return ApiResponse.success(trackService.getPosts());
    }

    @Operation(summary = "음원 수정")
    @PutMapping("/api/post/{boardId}")
    public ApiResponse updatePost(@PathVariable Long boardId, @RequestBody TrackCreateRequest requestsDto, @AuthenticationPrincipal User user) {
        return ApiResponse.success(trackService.updatePost(boardId, requestsDto, new BigInteger(user.getUsername())));
    }

    @Operation(summary = "음원 삭제")
    @DeleteMapping("/api/post/{boardId}")
    public ApiResponse deletePost(@PathVariable Long boardId, @AuthenticationPrincipal User user) {
        return ApiResponse.success(trackService.deletePost(boardId, new BigInteger(user.getUsername())));
    }
}
