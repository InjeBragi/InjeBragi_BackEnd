package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.CommentRequest;
import com.inje.bragi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Tag(name = "댓글 API")
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/comment/{boardId}")
    public ApiResponse createComment(@PathVariable Long boardId, @RequestBody CommentRequest requestDto, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.createComment(boardId, requestDto, new BigInteger(user.getUsername())));
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/comment/{commentId}")
    public ApiResponse updateComment(@PathVariable Long commentId, @RequestBody CommentRequest requestDto, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.updateComment(commentId, requestDto, new BigInteger(user.getUsername())));
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comment/{commentId}")
    public ApiResponse deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.deleteComment(commentId, new BigInteger(user.getUsername())));
    }
}
