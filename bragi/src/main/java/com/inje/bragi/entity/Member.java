package com.inje.bragi.entity;

import com.inje.bragi.common.MemberType;
import com.inje.bragi.dto.request.MemberUpdateRequest;
import com.inje.bragi.dto.request.SignUpRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member {

    @Id
    @SequenceGenerator(name = "member_id_seq", sequenceName = "idx_member", allocationSize = 1)
    @GeneratedValue
    private BigInteger id;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String password;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Image image;

    private Integer receivedLikeCnt;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Track> tracks;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Like> likes;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Comment> comments;

    public static Member from(SignUpRequest request, PasswordEncoder encoder) {
        return Member.builder()
                .account(request.account())
                .password(encoder.encode(request.password()))
                .name(request.name())
                .age(request.age())
                .type(MemberType.USER)
                .build();
    }

    public void update(MemberUpdateRequest newMember, PasswordEncoder encoder) {
        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank()
                ? this.password : encoder.encode(newMember.newPassword());
        this.name = newMember.name();
        this.age = newMember.age();
    }

    public void likeChange(Integer receivedLikeCnt) {
        this.receivedLikeCnt = receivedLikeCnt;
    }
}
