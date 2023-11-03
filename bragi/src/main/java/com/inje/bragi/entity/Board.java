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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Board {

    @Id
    @SequenceGenerator(name = "board_id_seq", sequenceName = "idx_board", allocationSize = 1)
    @GeneratedValue
    private BigInteger id;

    private Long boardId;
}
