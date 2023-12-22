package com.inje.bragi.dto.response;

import com.inje.bragi.entity.Track;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TrackResponse {
    private Long id;
    private String title;
    private String body;
    private String memberName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private String image;

    private String musicUrl;

    @Builder
    private TrackResponse(Track entity, List<CommentResponse> list) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.body = entity.getBody();
        this.memberName = entity.getMember().getName();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getLastModifiedAt();
    }

    @Builder
    private TrackResponse(Track entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.body = entity.getBody();
        this.memberName = entity.getMember().getName();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getLastModifiedAt();
    }

    public static TrackResponse from(Track entity, List<CommentResponse> list) {
        return TrackResponse.builder()
                .entity(entity)
                .list(list)
                .build();
    }

    public static TrackResponse from(Track entity) {
        return new TrackResponse(entity);
    }
}
