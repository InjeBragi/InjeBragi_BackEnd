package com.inje.bragi.dto.response;

import com.inje.bragi.entity.Mp3;
import lombok.Builder;

public class Mp3UploadResponse {
    private String url;

    @Builder
    public Mp3UploadResponse(String url) {
        this.url = url;
    }

    public static Mp3UploadResponse from(Mp3 mp3) {
        return new Mp3UploadResponse(
                mp3.getUrl()
        );
    }
}
