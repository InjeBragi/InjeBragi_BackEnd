package com.inje.bragi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UploadImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @Column(nullable = false)
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRACK_ID")
    private Track track;

    public void updateUrl(String url) {
        this.url = url;
    }

}
