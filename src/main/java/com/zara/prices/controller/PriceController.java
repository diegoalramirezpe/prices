package com.zara.prices.controller;

import com.zara.prices.model.Price;
import com.zara.prices.model.PriceResponse;
import com.zara.prices.repository.PriceRepository;
import com.zara.prices.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/price")
    @ResponseBody
    public PriceResponse getPrice(@RequestParam Long productId, @RequestParam Long brandId, @RequestParam String applicationDate) {
        log.info("Comienza la ejecucion del controlador para obtener el precio segun el productId {}, brandId {}, y applicationDate{}",
                productId, brandId, applicationDate);
        LocalDateTime date = LocalDateTime.parse(applicationDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        PriceResponse response = priceService.getPrice(productId, brandId, date);
        System.out.println("Respuesta del controlador: " + response);
        return response;
    }

    @GetMapping("/all")
    public List<Price> getAllPrices(){
        return priceService.getAllArticles();
    }

}