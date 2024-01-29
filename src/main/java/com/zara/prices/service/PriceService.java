package com.zara.prices.service;

import com.zara.prices.mapper.PriceResponseMapper;
import com.zara.prices.model.Price;
import com.zara.prices.model.PriceResponse;
import com.zara.prices.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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
        log.info("Comienza la ejecucion del servicio para obtener el precio segun el productId {}, brandId {}, y applicationDate{}",
                productId, brandId, applicationDate);
        final List<Price> priceList = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, applicationDate, applicationDate);

        log.info("Respuesta de la base de datos: {}",
                priceList);

        if (!priceList.isEmpty()){
            return priceResponseMapper.toResponse(priceList.get(0), applicationDate);
        }

        return null;
    }

    public List<Price> getAllArticles() {
        return priceRepository.findAll();
    }

}