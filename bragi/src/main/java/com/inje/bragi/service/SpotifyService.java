package com.inje.bragi.service;

import com.inje.bragi.dto.response.SearchTracksResponse;
import com.inje.bragi.entity.Song;
import com.inje.bragi.repository.SongRepository;
import com.inje.bragi.spotify.SpotifyConfig;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SpotifyService {

    private final SongRepository songRepository;

    @Transactional
    public List<SearchTracksResponse> search(String keyword) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.accessToken())
                .build();
        List <SearchTracksResponse> searchTracksResponses = new ArrayList<>();
        Song song = new Song();
        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(keyword)
                    .limit(10)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String title = track.getName();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();


                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();
                song = songRepository.save(Song.from(artistName, title, albumName, imageUrl));
                searchTracksResponses.add(SearchTracksResponse.from(song));
            }


        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (org.apache.hc.core5.http.ParseException e) {
            throw new RuntimeException(e);
        }

        return searchTracksResponses;
    }
}
