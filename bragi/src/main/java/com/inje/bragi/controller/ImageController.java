package com.inje.bragi.controller;

import com.inje.bragi.dto.ApiResponse;
import com.inje.bragi.dto.request.ImageUploadRequest;
import com.inje.bragi.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

@Tag(name = "프로필 이미지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @Value("${file.profileImagePath}")
    private String uploadPath;

    @Operation(summary = "프로필 이미지 변경")
    @PostMapping(name = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse upload(@ModelAttribute ImageUploadRequest imageUploadRequest, @AuthenticationPrincipal User user) {
        return ApiResponse.success(imageService.upload(imageUploadRequest, new BigInteger(user.getUsername())));
    }

    @GetMapping("/decode")
    public void decodeBase64ToImage(@RequestParam String base64String)throws IOException{
        byte[] decodedBytes = Base64.decodeBase64(base64String);
        Files.write(Paths.get(uploadPath), decodedBytes);
    }


    @GetMapping("/images/profileImage/{imageName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) {
        try {
            Resource imageResource = new ClassPathResource(uploadPath + imageName);

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
