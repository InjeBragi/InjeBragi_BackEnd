package com.inje.bragi.dto.response;

import com.inje.bragi.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageUploadResponse {

    private String url;

    @Builder
    public ImageUploadResponse(String url) {
        this.url = url;
    }

    public static ImageUploadResponse from(Image image) {
        return new ImageUploadResponse(
                image.getUrl()
        );
    }
}
