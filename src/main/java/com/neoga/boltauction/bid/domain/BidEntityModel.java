package com.neoga.boltauction.bid.domain;

import com.neoga.boltauction.bid.controller.BidController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class BidEntityModel extends EntityModel {

    public BidEntityModel(BidDto bidDto, Link... links) {
        super(bidDto, links);
        //add(linkTo(BidController.class).slash(bidDto.getBidId()).withSelfRel());
    }
}
