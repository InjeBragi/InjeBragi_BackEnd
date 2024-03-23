package com.inje.bragi.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TrackCreateRequest {
    private String title;

    private String body;

    private String memberProfileUrl;

    private String image;

    private MultipartFile file;
}

