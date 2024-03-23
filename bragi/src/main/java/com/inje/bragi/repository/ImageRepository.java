package com.inje.bragi.repository;

import com.inje.bragi.entity.Image;
import com.inje.bragi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByMember(Member member);
}
