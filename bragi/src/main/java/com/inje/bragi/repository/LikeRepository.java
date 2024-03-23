package com.inje.bragi.repository;

import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Like;
import com.inje.bragi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndMember(Board board, Member member);
    Optional<Like> findByCommentAndMember(Comment comment, Member member);

    void deleteAllByMember(Member member);
}
