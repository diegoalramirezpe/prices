package com.zara.prices.mapper;

import com.zara.prices.model.Price;
import com.zara.prices.model.PriceResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceResponseMapper {

    public PriceResponse toResponse(Price price, LocalDateTime applicationDate){
        return PriceResponse.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .applicationDate(applicationDate)
                .price(price.getPrice())
                .build();
    }
}
