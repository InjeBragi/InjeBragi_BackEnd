package com.inje.bragi.service;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.common.SuccessResponse;
import com.inje.bragi.dto.request.CommentRequest;
import com.inje.bragi.dto.response.CommentResponse;
import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Member;
import com.inje.bragi.entity.enumSet.ErrorType;
import com.inje.bragi.handler.RestApiException;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.CommentRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    String notExist = "계정이 존재하지 않습니다.";

    @Transactional
    public CommentResponse createComment(Long id, CommentRequest requestDto, BigInteger account) {
        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));

        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Comment comment = Comment.of(requestDto, board.get(), member);
        commentRepository.save(comment);

        return CommentResponse.from(comment);

    }

    @Transactional
    public CommentResponse updateComment(Long id, CommentRequest requestDto, BigInteger account) {
        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));

        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Optional<Comment> found = commentRepository.findByIdAndMember(id, member);
        if (found.isEmpty() && member.getType() == MemberType.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }

        comment.get().update(requestDto, member);
        commentRepository.flush();

        return CommentResponse.from(comment.get());

    }

    @Transactional
    public SuccessResponse deleteComment(Long id, BigInteger account) {
        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));

        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Optional<Comment> found = commentRepository.findByIdAndMember(id, member);
        if (found.isEmpty() && member.getType() == MemberType.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }

        commentRepository.deleteById(id);

        return SuccessResponse.of(HttpStatus.OK, "댓글 삭제 성공");

    }


}
