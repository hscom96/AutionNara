package com.neoga.boltauction.bid.dto;

import lombok.Data;

@Data
public class BidDto {
    private Long bidId;
    //private Long memberId;
    private Long itemId;
    private int price;
}
