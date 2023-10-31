package com.inje.bragi.repository;

import com.inje.bragi.entity.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMember, BigInteger> {
    Optional<AuthMember> findByEmailAndProvider(String email, String provider);
}
