package com.neoga.boltacution.memberstore.member.domain;

import com.neoga.boltacution.memberstore.store.domain.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Members {
    @Column(name="member_id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> role;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwd;
    private String name;
    @Column(name="create_date")
    private LocalDateTime createDate;
    @Column(name="change_date")
    private LocalDateTime changeDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}