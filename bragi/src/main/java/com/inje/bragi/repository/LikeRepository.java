package com.inje.bragi.repository;

import com.inje.bragi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByMemberAccountAndBoardId(String loginId, Long boardId);
    Boolean existsByMemberAccountAndBoardId(String loginId, Long boardId);
    List<Like> findAllByMemberAccount(String loginId);
}
