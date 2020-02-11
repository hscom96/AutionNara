package com.neoga.boltauction.bid.service;

import org.springframework.http.ResponseEntity;

public interface BidService {
    public ResponseEntity getBidList(Long id);

}
