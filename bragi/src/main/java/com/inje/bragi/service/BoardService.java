package com.inje.bragi.service;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.dto.request.BoardCreateRequest;
import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.BoardRepository;
import com.inje.bragi.repository.CommentRepository;
import com.inje.bragi.repository.LikeRepository;
import com.inje.bragi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    @Value("${classpath:profileImages/}")
    private String uploadFolder;


    public Page<Board> getBoardList(PageRequest pageRequest, String searchType, String keyword) {
        if (searchType != null && keyword != null) {
            if (searchType.equals("title")) {
                return boardRepository.findAllByTitleContainsAndMemberTypeNot(keyword, MemberType.ADMIN, pageRequest);
            } else {
                return boardRepository.findAllByMemberNameContainsAndMemberTypeNot(keyword, MemberType.ADMIN, pageRequest);
            }
        }
        return boardRepository.findAllByMemberTypeNot(MemberType.ADMIN, pageRequest);
    }



    @Transactional
    public Long writeBoard(BoardCreateRequest req, BigInteger account) throws IOException {
        Member loginUser = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다."));

        Board savedBoard = boardRepository.save(req.toEntity(loginUser));

        // Save image to local directory
        /*MultipartFile uploadImage = req.getUploadImage();
        if (uploadImage != null && !uploadImage.isEmpty()) {
            String fileName = StringUtils.cleanPath(uploadImage.getOriginalFilename());
            Path uploadPath = Paths.get(uploadFolder);

            // Create the directory if it doesn't exist
            Files.createDirectories(uploadPath);

            // Resolve the path for the new file
            Path filePath = uploadPath.resolve(fileName);

            // Copy the file to the upload path
            Files.copy(uploadImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            //savedBoard.setLocalImagePath(filePath.toString());
        }*/

        return savedBoard.getId();
    }
}
