package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.ImageUploadRequest;
import com.inje.bragi.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "프로필 이미지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Value("${classpath:profileImages/}")
    private String uploadPath;

    @Operation(summary = "프로필 이미지 변경")
    @PostMapping("/upload")
    public ApiResponse upload(@ModelAttribute ImageUploadRequest imageUploadRequest, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ApiResponse.success(imageService.upload(imageUploadRequest, userDetails.getUsername()));
    }

    @GetMapping("/images/{imageName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) {
        try {
            Resource imageResource = new ClassPathResource("profileImages/" + imageName);

            if (imageResource.exists() && imageResource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // 이미지 유형에 따라 변경
                        .body(imageResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
