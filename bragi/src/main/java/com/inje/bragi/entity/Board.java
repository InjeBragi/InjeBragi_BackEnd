package com.inje.bragi.entity;

import com.inje.bragi.dto.BoardDto;
import com.inje.bragi.dto.request.BoardCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Board extends BaseEntity{

    @Id
    @SequenceGenerator(name = "board_id", sequenceName = "idx_board", allocationSize = 1)
    @GeneratedValue
    private Long id;

    @Column
    private String title;
    @Column
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Likes> likes = new ArrayList<>();


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();



    public void update(BoardDto dto) {
        this.title = dto.getTitle();
        this.body = dto.getBody();
    }

    @Builder
    private Board(BoardCreateRequest requestsDto, Member member) {
        this.title = requestsDto.getTitle();
        this.body = requestsDto.getBody();
        this.member = member;
    }

    public void update(BoardCreateRequest requestsDto, Member member) {
        this.title = requestsDto.getTitle();
        this.body = requestsDto.getBody();
        this.member = member;
    }

    public static Board of(BoardCreateRequest requestsDto, Member member) {
        return Board.builder()
                .title(requestsDto.getTitle())
                .body(requestsDto.getBody())
                .member(member)
                .build();
    }

}
