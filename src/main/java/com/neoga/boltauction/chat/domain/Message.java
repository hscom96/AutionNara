package com.neoga.boltauction.chat.domain;

import com.neoga.boltauction.memberstore.member.domain.Members;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String content;
    @Column(name="create_date")
    private LocalDateTime createDt;
    @Column(name="is_read")
    private boolean isRead;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Room room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Members sender;
}
