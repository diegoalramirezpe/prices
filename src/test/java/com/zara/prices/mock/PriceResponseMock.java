package com.zara.prices.mock;

import com.zara.prices.model.PriceResponse;

import java.time.LocalDateTime;

public class PriceResponseMock {

    public static PriceResponse caseOnePriceResponse(double price){
        return PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .price(price)
                .build();
    }
}
