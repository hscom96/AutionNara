package com.neoga.boltauction.bid.domain;

import lombok.Data;

@Data
public class BidDto {
    private Long bidId;
    //private Long memberId;
    private Long itemId;
    private int price;
}
