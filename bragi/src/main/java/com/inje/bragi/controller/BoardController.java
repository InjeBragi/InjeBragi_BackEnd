package com.inje.bragi.controller;


import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.BoardCreateRequest;
import com.inje.bragi.repository.MemberRepository;
import com.inje.bragi.service.BoardService;
import com.inje.bragi.service.CommentService;
import com.inje.bragi.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final MemberRepository memberRepository;

    @PostMapping("/write")
    public ApiResponse boardWrite(@ModelAttribute BoardCreateRequest req, @AuthenticationPrincipal User user) throws IOException {
        return ApiResponse.success(boardService.writeBoard(req, new BigInteger(user.getUsername())));
    }
}
