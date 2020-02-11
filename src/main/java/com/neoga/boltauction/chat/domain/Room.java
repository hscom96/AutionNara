package com.neoga.boltauction.chat.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime createDt;
}
