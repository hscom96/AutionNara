package com.neoga.boltauction.item.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class InsertItemDto {

    @NotNull(message = "item name is not null")
    private String itemName;
    @NotNull(message = "description is not null")
    private String description;
    @NotNull(message = "quick price is not null")
    @Positive
    private int quickPrice;
    @NotNull(message = "start price is not null")
    @Positive
    private int startPrice;
    @NotNull(message = "min bid price is not null")
    @Positive
    private int minBidPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "end date is not null")
    @Future
    private LocalDateTime endDt;
    @NotNull(message = "category id is not null")
    private Long categoryId;
    //private Long memberId;
}
