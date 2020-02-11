package com.neoga.boltauction.bid.controller;

import com.neoga.boltauction.bid.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bid")
public class BidController {

    private final BidService bidService;

    @GetMapping("/{item-id}")
    public ResponseEntity getBidList(@PathVariable(name = "item-id") Long itemId) {
       return bidService.getBidList(itemId);
    }

    @PostMapping("/{item-id}")
    public ResponseEntity 입찰_등록() {
        return null;
    }

    @DeleteMapping("/{item-id")
    public ResponseEntity 입찰_삭제() {
        return null;
    }
}
