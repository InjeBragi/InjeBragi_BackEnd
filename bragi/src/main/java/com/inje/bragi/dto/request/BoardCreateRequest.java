package com.inje.bragi.dto.request;

import lombok.Data;

@Data
public class BoardCreateRequest {

    private String title;

    private String body;

    private String memberProfileUrl;

    private String musicArtist;

    private String musicTitle;

    private String musicUrl;

    private String image;
}
