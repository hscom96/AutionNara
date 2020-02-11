package com.neoga.boltauction.image.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String path;
}
