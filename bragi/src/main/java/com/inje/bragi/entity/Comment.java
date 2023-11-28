package com.inje.bragi.entity;

import com.inje.bragi.dto.request.CommentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment extends BaseEntity {

    @Id
    @SequenceGenerator(name = "comment_id", sequenceName = "idx_comment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    private Comment(CommentRequest requestDto, Board board, Member member) {
        this.body = requestDto.getBody();
        this.board = board;
        this.member = member;
    }

    public void update(CommentRequest requestDto, Member member) {
        this.body = requestDto.getBody();
        this.member = member;
    }

    public static Comment of(CommentRequest requestDto, Board board, Member member) {
        Comment comment = Comment.builder()
                .body(requestDto.getBody())
                .board(board)
                .member(member)
                .build();
        board.getComments().add(comment);
        return comment;
    }
}
