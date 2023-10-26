package com.inje.bragi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Song {
    @Id
    @SequenceGenerator(name = "song_id_seq", sequenceName = "idx_song", allocationSize = 1)
    @GeneratedValue
    private BigInteger id;

    String artistName;

    String title;

    String albumName;

    String imageUrl;

    public static Song from(String artistName, String title, String albumName, String imageUrl) {
        return Song.builder()
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .build();
    }

}
