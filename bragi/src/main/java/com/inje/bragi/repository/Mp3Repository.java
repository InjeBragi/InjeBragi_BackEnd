package com.inje.bragi.repository;

import com.inje.bragi.entity.Mp3;
import com.inje.bragi.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mp3Repository extends JpaRepository<Mp3, Long> {
    Mp3 findByTrack(Track track);
}
