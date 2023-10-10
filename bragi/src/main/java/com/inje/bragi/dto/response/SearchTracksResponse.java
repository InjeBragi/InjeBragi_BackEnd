package com.inje.bragi.dto.response;

import com.inje.bragi.entity.Song;

public record SearchTracksResponse(
        String artistName,
        String title,
        String albumName,
        String imageUrl
) {
    public static SearchTracksResponse from(Song song){
        return new SearchTracksResponse(
                song.getArtistName()
                ,song.getTitle()
                ,song.getAlbumName(),
                song.getImageUrl());
    }
}
