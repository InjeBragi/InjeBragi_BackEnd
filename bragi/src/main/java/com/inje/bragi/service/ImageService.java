package com.inje.bragi.service;

import com.inje.bragi.dto.request.ImageUploadRequest;
import com.inje.bragi.dto.response.ImageUploadResponse;
import com.inje.bragi.entity.Image;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.ImageRepository;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    private final ResourceLoader resourceLoader;

    @Value("${classpath:/profileImages/}")
    private String uploadFolder;

    public ImageUploadResponse upload(ImageUploadRequest imageUploadRequest, BigInteger account) {

        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));
        System.out.println(member.getName());
        MultipartFile file = imageUploadRequest.getFile();
        Image image;

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();


        try {
            Resource resource = resourceLoader.getResource(uploadFolder);
            String uploadFolderPath = resourceLoader.getResource(uploadFolder).getURL().getPath();

            File destinationFile = new File(uploadFolderPath, imageFileName);
            file.transferTo(destinationFile);
            if (!resource.exists()) {
                Files.createDirectories(resource.getFile().toPath());
            }

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
