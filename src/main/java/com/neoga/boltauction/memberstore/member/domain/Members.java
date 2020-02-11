package com.neoga.boltauction.memberstore.member.domain;

import com.neoga.boltauction.memberstore.store.domain.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Members {
    @Column(name="member_id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String passwd;
    private String name;
    @Column(name="create_date")
    private LocalDateTime createDt;
    @Column(name="change_date")
    private LocalDateTime changeDt;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
