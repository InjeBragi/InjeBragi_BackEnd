package com.inje.bragi.service;

import com.inje.bragi.dto.request.ImageUploadRequest;
import com.inje.bragi.dto.response.ImageUploadResponse;
import com.inje.bragi.entity.Image;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.ImageRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    private final ResourceLoader resourceLoader;

    @Value("${file.profileImagePath}")
    private String uploadFolder;

    public ImageUploadResponse upload(ImageUploadRequest imageUploadRequest, BigInteger account) {

        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));
        MultipartFile file = imageUploadRequest.getFile();
        Image image;
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        File destinationFile = new File(uploadFolder + imageFileName);

        try {
            file.transferTo(destinationFile);

            image = imageRepository.findByMember(member);

            image = imageRepository.findByMember(member);
            if (image != null) {
                image.updateUrl("/profileImages/" + imageFileName);
            } else {
                image = Image.builder()
                        .member(member)
                        .url("/profileImages/" + imageFileName)
                        .build();
            }
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ImageUploadResponse.from(image);
    }


    public ImageUploadResponse findImage(String account) {
        Member member = memberRepository.findByAccount(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));
        Image image = imageRepository.findByMember(member);

        String defaultImageUrl = "/profileImages/anonymous.png";

        if (image == null) {
            return ImageUploadResponse.builder()
                    .url(defaultImageUrl)
                    .build();
        } else {
            return ImageUploadResponse.builder()
                    .url(image.getUrl())
                    .build();
        }
    }
}
