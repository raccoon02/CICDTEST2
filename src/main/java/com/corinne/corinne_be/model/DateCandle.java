package com.corinne.corinne_be.model;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tbl_daycandle")
public class DateCandle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long dayCandleId;

    @Column(nullable = false)
    private String tiker;

    @Column(nullable = false)
    private int highPrice;

    @Column(nullable = false)
    private int lowPrice;

    @Column(nullable = false)
    private int startPrice;

    @Column(nullable = false)
    private int endPrice;

    @Column(nullable = false)
    private int tradeDate;

    @Column(nullable = false)
    private Long tradeVolume;
}