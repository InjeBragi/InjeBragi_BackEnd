package com.inje.bragi.repository;

import com.inje.bragi.entity.Comment;
import com.inje.bragi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndMember(Long id, Member member);

    void deleteAllByMember(Member member);
}
