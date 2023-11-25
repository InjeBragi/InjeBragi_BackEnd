package com.inje.bragi.repository;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByMemberTypeNot(MemberType type, PageRequest pageRequest);
    Page<Board> findAllByTitleContainsAndMemberTypeNot(String title, MemberType type, PageRequest pageRequest);
    Page<Board> findAllByMemberNameContainsAndMemberTypeNot(String name, MemberType type, PageRequest pageRequest);


    List<Board> findAllByMemberType(MemberType type);
    Long countAllByMemberType(MemberType type);
    Long countAllByMemberTypeNot(MemberType type);
}
