package com.inje.bragi.service;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.dto.request.CommentCreateRequest;
import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.CommentRepository;
import com.inje.bragi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void writeComment(Long boardId, CommentCreateRequest req, String account) {
        Board board = boardRepository.findById(boardId).get();
        Member user = memberRepository.findByAccount(account).get();
        board.commentChange(board.getCommentCnt() + 1);
        commentRepository.save(req.toEntity(board, user));
    }

    public List<Comment> findAll(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public Long editComment(Long commentId, String newBody, String account) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<Member> optUser = memberRepository.findByAccount(account);
        if (optComment.isEmpty() || optUser.isEmpty() || !optComment.get().getMember().equals(optUser.get())) {
            return null;
        }

        Comment comment = optComment.get();
        comment.update(newBody);

        return comment.getBoard().getId();
    }

    public Long deleteComment(Long commentId, String account) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<Member> optUser = memberRepository.findByAccount(account);
        if (optComment.isEmpty() || optUser.isEmpty() ||
                (!optComment.get().getMember().equals(optUser.get()) && !optUser.get().getType().equals(MemberType.ADMIN))) {
            return null;
        }

        Board board = optComment.get().getBoard();
        board.commentChange(board.getCommentCnt() - 1);

        commentRepository.delete(optComment.get());
        return board.getId();
    }
}
