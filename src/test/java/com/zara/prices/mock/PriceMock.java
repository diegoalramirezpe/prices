package com.zara.prices.mock;

import com.zara.prices.model.Price;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PriceMock {

    public static List<Price> anPriceList(){
       Price price = Price.builder()
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .endDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .priceList(1)
                        .productId(35455L)
                        .priority(0)
                        .price(35.5)
                        .currency("EUR")
                        .build();

        return Collections.singletonList(price);
    }
}
