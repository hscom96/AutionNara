package com.neoga.boltauction.bid.service;

import com.neoga.boltauction.bid.domain.Bid;
import com.neoga.boltauction.bid.dto.BidDto;
import com.neoga.boltauction.bid.repository.BidRepository;
import com.neoga.boltauction.item.domain.Item;
import com.neoga.boltauction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity getBidList(Long itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        Item findItem = optionalItem.get();

        List<Bid> findBidList = bidRepository.findAllByItemOrderByPriceAsc(findItem);
        List<BidDto> bidDtoList = findBidList.stream().map(bid -> {
            return modelMapper.map(bid, BidDto.class);
        }).collect(Collectors.toList());

        CollectionModel<BidDto> bidDtoCollectionModel = new CollectionModel<>(bidDtoList);

        return ResponseEntity.ok(bidDtoCollectionModel);
    }


}
