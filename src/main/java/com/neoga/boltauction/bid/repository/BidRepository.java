package com.neoga.boltauction.bid.repository;

import com.neoga.boltauction.bid.domain.Bid;
import com.neoga.boltauction.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    public List<Bid> findAllByItemOrderByPriceAsc(Item item);
}
