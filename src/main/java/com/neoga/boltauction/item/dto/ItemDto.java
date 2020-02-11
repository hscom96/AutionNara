package com.neoga.boltauction.item.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class ItemDto {

    private Long itemId;
    private String itemName;
    private String description;
    @Positive
    private int quickPrice;
    @Positive
    private int startPrice;
    @Positive
    private int minBidPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future
    private LocalDateTime endDt;
    private Long categoryId;
    //private Long memberId;
}
