package com.inje.bragi.service;

import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Like;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.LikeRepository;
import com.inje.bragi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void addLike(String account, Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        Member loginUser = memberRepository.findByAccount(account).get();
        Member boardUser = board.getMember();

        // 자신이 누른 좋아요가 아니라면
        if (!boardUser.equals(loginUser)) {
            boardUser.likeChange(boardUser.getReceivedLikeCnt() + 1);
        }
        board.likeChange(board.getLikeCnt() + 1);

        likeRepository.save(Like.builder()
                .member(loginUser)
                .board(board)
                .build());
    }

    @Transactional
    public void deleteLike(String account, Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        Member loginUser = memberRepository.findByAccount(account).get();
        Member boardUser = board.getMember();

        // 자신이 누른 좋아요가 아니라면
        if (!boardUser.equals(loginUser)) {
            boardUser.likeChange(boardUser.getReceivedLikeCnt() - 1);
        }
        board.likeChange(board.getLikeCnt() - 1);

        likeRepository.deleteByMemberAccountAndBoardId(account, boardId);
    }

    public Boolean checkLike(String loginId, Long boardId) {
        return likeRepository.existsByMemberAccountAndBoardId(loginId, boardId);
    }
}
