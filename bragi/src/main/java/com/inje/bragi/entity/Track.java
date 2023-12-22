package com.inje.bragi.entity;

import com.inje.bragi.dto.request.TrackCreateRequest;
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
public class Track extends BaseEntity{

    @Id
    @SequenceGenerator(name = "track_id", sequenceName = "idx_track", allocationSize = 1)
    @GeneratedValue
    private Long id;

    @Column
    private String title;
    @Column
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(mappedBy = "track", fetch = FetchType.LAZY)
    private Mp3 mp3;


    public void update(TrackCreateRequest requestsDto, Member member) {
        this.title = requestsDto.getTitle();
        this.body = requestsDto.getBody();
        this.member = member;
    }

    public static Track of(TrackCreateRequest requestsDto, Member member) {
        return Track.builder()
                .title(requestsDto.getTitle())
                .body(requestsDto.getBody())
                .member(member)
                .build();
    }

}
