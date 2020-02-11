package com.neoga.boltauction.memberstore.store.domain;

import com.neoga.boltauction.memberstore.member.domain.Members;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy="store", fetch = FetchType.LAZY)
    private Members members;
}
