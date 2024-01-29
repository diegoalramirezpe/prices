package com.zara.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.prices.config.DataInitializer;
import com.zara.prices.controller.PriceController;
import com.zara.prices.mapper.PriceResponseMapper;
import com.zara.prices.mock.PriceResponseMock;
import com.zara.prices.model.Price;
import com.zara.prices.repository.PriceRepository;
import com.zara.prices.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PriceController.class)
@AutoConfigureMockMvc
@ContextConfiguration(locations = "/test-context.xml")
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

     @Autowired
     private ObjectMapper objectMapper;

    @MockBean
    private PriceController priceController;

    @MockBean
    private PriceService priceService;

    @MockBean
    private  PriceResponseMapper priceResponseMapper;

    @MockBean
    private PriceRepository priceRepository;

    @MockBean
    private DataInitializer dataInitializer;

    @BeforeEach
    public void init(){
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

    @Test
    public void testConsultaPrecio() throws Exception {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)

        when(priceService.getPrice(any(),any(),any())).thenReturn(PriceResponseMock.caseOnePriceResponse());


        final MvcResult result = mockMvc.perform(get("/price")
                .queryParam("brandId", "1")
                .queryParam("productId","35455")
                .queryParam("applicationDate", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        final String response = result.getResponse().getContentAsString();

        mockMvc.perform(get("/price")
                        .queryParam("brandId", "1")
                        .queryParam("productId","35455")
                        .queryParam("applicationDate", "2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.applicationDate").value("2020-06-14T10:00:00"));


        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        mockMvc.perform(get("/price")
                        .param("brandId", "1")
                        .param("productId","35455")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.applicationDate").value("2020-06-14T16:00:00"));

        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        mockMvc.perform(get("/price")
                        .param("brandId", "1")
                        .param("productId","35455")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.applicationDate").value("2020-06-14T21:00:00"));

        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        mockMvc.perform(get("/price")
                        .param("brandId", "1")
                        .param("productId","35455")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.applicationDate").value("2020-06-15T10:00:00"));

        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        mockMvc.perform(get("/price")
                        .param("brandId", "1")
                        .param("productId","35455")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.applicationDate").value("2020-06-16T21:00:00"));


    }
}
