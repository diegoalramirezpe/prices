package com.zara.prices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@Builder
@Getter
public class PriceResponse implements Serializable {

    private Long productId;

    private Long brandId;

    private Integer priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime applicationDate;

    private Double price;
}
