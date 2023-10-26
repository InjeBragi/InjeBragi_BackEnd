package com.inje.bragi.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {
    private MultipartFile file;
}
