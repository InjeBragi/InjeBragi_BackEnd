package com.inje.bragi.repository;

import com.inje.bragi.entity.Board;
import com.inje.bragi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByLastModifiedAtDesc();

    Optional<Board> findByIdAndMember(Long id, Member member);

    void deleteAllByMember(Member member);

}
