package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.CommentRequest;
import com.inje.bragi.service.BoardService;
import com.inje.bragi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    // 댓글 작성
    @PostMapping("/comment/{id}")   // 여기서 ID는 게시글의 id
    public ApiResponse createComment(@PathVariable Long id, @RequestBody CommentRequest requestDto, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.createComment(id, requestDto, new BigInteger(user.getUsername())));
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")    // 여기서 ID는 댓글의 id
    public ApiResponse updateComment(@PathVariable Long id, @RequestBody CommentRequest requestDto, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.updateComment(id, requestDto, new BigInteger(user.getUsername())));
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{id}")     // 여기서 ID는 댓글의 id
    public ApiResponse deleteComment(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ApiResponse.success(commentService.deleteComment(id, new BigInteger(user.getUsername())));
    }
}
