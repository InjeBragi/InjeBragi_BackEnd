package com.inje.bragi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "idx_comment", allocationSize = 1)
    @GeneratedValue
    private BigInteger id;

    private Long boardId;

    private Long loginId;



}
