package com.inje.bragi.repository;

import com.inje.bragi.entity.Mp3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface Mp3Repository extends JpaRepository<Mp3, BigInteger> {

    Mp3 findByTrackId(BigInteger id);

}
