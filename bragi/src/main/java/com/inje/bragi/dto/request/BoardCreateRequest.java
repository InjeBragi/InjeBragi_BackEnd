package com.inje.bragi.dto.request;

import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Member;
import lombok.Data;

@Data
public class BoardCreateRequest {

    private String title;
    private String body;

    public Board toEntity(Member member) {
        return Board.builder()
                .member(member)
                .title(title)
                .body(body)
                .build();
    }
}
