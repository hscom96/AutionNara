package com.neoga.boltacution.order.domain;

import com.neoga.boltacution.item.domain.Item;
import com.neoga.boltacution.memberstore.member.domain.Members;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Orders {
    @Column(name="order_id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Members members;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}