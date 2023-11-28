package com.inje.bragi.service;

import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.LikeRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


}
