package com.inje.bragi.dto.response;


import com.inje.bragi.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private String body;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    private CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.body = entity.getBody();
        this.memberName = entity.getMember().getName();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getLastModifiedAt();
    }

    public static CommentResponse from(Comment entity) {
        return CommentResponse.builder()
                .entity(entity)
                .build();
    }

}
