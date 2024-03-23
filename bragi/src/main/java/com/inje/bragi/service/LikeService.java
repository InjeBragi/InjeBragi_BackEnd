package com.inje.bragi.service;

import com.inje.bragi.dto.response.BoardResponse;
import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Like;
import com.inje.bragi.entity.Member;
import com.inje.bragi.entity.enumSet.ErrorType;
import com.inje.bragi.handler.RestApiException;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.LikeRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final MemberRepository memberRepository;
    private final LikeRepository likesRepository;
    private final BoardRepository boardRepository;


    public BoardResponse likePost(Long id, BigInteger account) {
        Member loginUser = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Optional<Like> found = likesRepository.findByBoardAndMember(board.get(), loginUser);
        if (found.isEmpty()) {
            Like likes = Like.of(board.get(), loginUser);
            likesRepository.save(likes);
        } else {
            likesRepository.delete(found.get());
            likesRepository.flush();
        }
        return BoardResponse.from(board.get());
    }
}
