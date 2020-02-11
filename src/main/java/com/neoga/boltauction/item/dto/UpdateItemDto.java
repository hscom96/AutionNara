package com.neoga.boltauction.item.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Data
public class UpdateItemDto {

    private String itemName;
    private String description;
    @PositiveOrZero
    private int quickPrice;
    @PositiveOrZero
    private int startPrice;
    @PositiveOrZero
    private int minBidPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Future
    private LocalDateTime endDt;
    private Long categoryId;
    //private Long memberId;
}
