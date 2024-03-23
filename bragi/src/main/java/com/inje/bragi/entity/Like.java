package com.inje.bragi.entity;

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
@Table(name = "\"like\"")
public class Like {

    @Id
    @SequenceGenerator(name = "like_id", sequenceName = "idx_like", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    private Like(Board board, Comment comment, Member member) {
        this.board = board;
        this.comment = comment;
        this.member = member;
    }

    public static Like of(Board board, Member member) {
        Like likes = Like.builder()
                .board(board)
                .member(member)
                .build();
        board.getLikes().add(likes);
        return likes;
    }

    public static Like of(Comment comment, Member member) {
        Like likes = Like.builder()
                .comment(comment)
                .member(member)
                .build();
        return likes;
    }

}

