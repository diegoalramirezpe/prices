package com.zara.prices.service;

import com.zara.prices.mapper.PriceResponseMapper;
import com.zara.prices.model.Price;
import com.zara.prices.model.PriceResponse;
import com.zara.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private final PriceResponseMapper priceResponseMapper;

    public PriceService(PriceRepository priceRepository, PriceResponseMapper priceResponseMapper) {
        this.priceRepository = priceRepository;
        this.priceResponseMapper = priceResponseMapper;
    }

    public PriceResponse getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        final List<Price> priceList =priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId,productId,applicationDate,applicationDate);
        return priceResponseMapper.toResponse(priceList.get(0),applicationDate);
    }

    public List<Price> getAllArticles() {
        return priceRepository.findAll();
    }

}