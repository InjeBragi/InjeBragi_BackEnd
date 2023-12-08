package com.inje.bragi.service;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.dto.request.BoardCreateRequest;
import com.inje.bragi.dto.response.BoardResponse;
import com.inje.bragi.dto.response.CommentResponse;
import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Member;
import com.inje.bragi.entity.enumSet.ErrorType;
import com.inje.bragi.handler.RestApiException;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    String notExist = "계정이 존재하지 않습니다.";

    @Value("${file.profileImagePath}")
    private String uploadFolder;

    @Transactional
    public Long writeBoard(BoardCreateRequest req, BigInteger account) {

        Member loginUser = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));


        Board savedBoard = boardRepository.save(Board.of(req, loginUser));

        return savedBoard.getId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getPosts() {

        List<Board> boardList = boardRepository.findAllByOrderByLastModifiedAtDesc();
        List<BoardResponse> responseDtoList = new ArrayList<>();

        for (Board board : boardList) {
            board.getComments()
                    .sort(Comparator.comparing(Comment::getLastModifiedAt)
                            .reversed());

            List<CommentResponse> commentList = new ArrayList<>();

            responseDtoList.add(BoardResponse.from(board, commentList));
        }

        return responseDtoList;
    }

    @Transactional
    public BoardResponse updatePost(Long id, BoardCreateRequest requestsDto, BigInteger account) {
        Member loginUser = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));

        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Optional<Board> found = boardRepository.findByIdAndMember(id, loginUser);
        if (found.isEmpty() && loginUser.getType() == MemberType.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }
        board.get().update(requestsDto, loginUser);
        boardRepository.flush();

        return BoardResponse.from(board.get());

    }

    @Transactional
    public BoardResponse deletePost(Long id, BigInteger account) {
        Member loginUser = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException(notExist));

        Optional<Board> found = boardRepository.findById(id);
        if (found.isEmpty()) {
            throw new RestApiException(ErrorType.NOT_FOUND_WRITING);
        }

        Optional<Board> board = boardRepository.findByIdAndMember(id, loginUser);
        if (board.isEmpty() && loginUser.getType() == MemberType.USER) {
            throw new RestApiException(ErrorType.NOT_WRITER);
        }

        boardRepository.deleteById(id);

        return BoardResponse.from(board.get());
    }
}
