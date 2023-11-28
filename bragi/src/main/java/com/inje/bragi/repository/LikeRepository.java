package com.inje.bragi.repository;

import com.inje.bragi.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    void deleteByMemberAccountAndBoardId(String loginId, Long boardId);
    Boolean existsByMemberAccountAndBoardId(String loginId, Long boardId);
    List<Likes> findAllByMemberAccount(String loginId);
}
