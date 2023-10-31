/*
package com.inje.bragi;//package com.auth.study;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.entity.Member;
import com.inje.bragi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AdminInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {

        memberRepository.save(Member.builder()
                .account("admin")
                .password((encoder.encode("admin")))
                .email("admin@admin.com")
                .name("관리자")
                .type(MemberType.ADMIN)
                .build());
    }
}
*/
