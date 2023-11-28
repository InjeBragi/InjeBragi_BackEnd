package com.inje.bragi.dto.request;

import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Member;
import lombok.Data;

@Data
public class CommentCreateRequest {

    private String body;

    Long parentCommentId;

    public Comment toEntity(Board board, Member member) {
        return Comment.builder()
                .member(member)
                .board(board)
                .body(body)
                .build();
    }
}
