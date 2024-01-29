package com.zara.prices.config;

import com.zara.prices.model.Price;
import com.zara.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Price> prices = Arrays.asList(
                Price.builder()
                    .brandId(1L)
                    .startDate(LocalDateTime.parse("2020-06-14T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .endDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .priceList(1)
                    .productId(35455L)
                    .priority(0)
                    .price(35.5)
                    .currency("EUR")
                    .build(),
                Price.builder()
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2020-06-14T15:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .endDate(LocalDateTime.parse("2020-06-14T18:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .priceList(2)
                        .productId(35455L)
                        .priority(1)
                        .price(25.45)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2020-06-15T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .endDate(LocalDateTime.parse("2020-06-15T11:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .priceList(3)
                        .productId(35455L)
                        .priority(1)
                        .price(30.5)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .brandId(1L)
                        .startDate(LocalDateTime.parse("2020-06-15T16:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .endDate(LocalDateTime.parse("2020-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .priceList(4)
                        .productId(35455L)
                        .priority(1)
                        .price(38.95)
                        .currency("EUR")
                        .build()
        );

        priceRepository.saveAll(prices);
    }

}