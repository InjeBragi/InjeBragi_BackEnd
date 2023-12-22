package com.inje.bragi.repository;

import com.inje.bragi.entity.Member;
import com.inje.bragi.entity.Mp3;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mp3Repository extends JpaRepository<Mp3, Long> {
    Mp3 findByMember(Member member);
}
