package com.inje.bragi.repository;

import com.inje.bragi.entity.Member;
import com.inje.bragi.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, BigInteger> {
    List<Track> findAllByOrderByLastModifiedAtDesc();

    Optional<Track> findByIdAndMember(BigInteger id, Member member);

    void deleteAllByMember(Member member);

    Track findByMember(Member member);

    Track findByMp3_Id(Long mp3Id);
}
