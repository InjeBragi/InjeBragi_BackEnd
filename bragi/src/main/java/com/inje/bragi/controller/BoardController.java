package com.inje.bragi.controller;


import com.inje.bragi.dto.request.BoardCreateRequest;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.MemberRepository;
import com.inje.bragi.service.BoardService;
import com.inje.bragi.service.CommentService;
import com.inje.bragi.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final MemberRepository memberRepository;

    @PostMapping("/write")
    public String boardWrite(@ModelAttribute BoardCreateRequest req, @AuthenticationPrincipal Member member, Model model) throws IOException {
        Long savedBoardId = boardService.writeBoard(req, member.getAccount(), member);
        model.addAttribute("message", savedBoardId + "번 글이 등록되었습니다.");
        model.addAttribute("nextUrl", "/boards/" + "/" + savedBoardId);
        return "printMessage";
    }
}
