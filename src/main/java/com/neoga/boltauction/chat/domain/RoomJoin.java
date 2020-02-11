package com.neoga.boltauction.chat.domain;


import com.neoga.boltauction.memberstore.member.domain.Members;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="room_join")
public class RoomJoin {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;
}