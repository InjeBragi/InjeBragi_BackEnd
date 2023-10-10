package com.inje.bragi.repository;

import com.inje.bragi.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SongRepository extends JpaRepository<Song, BigInteger> {

}
