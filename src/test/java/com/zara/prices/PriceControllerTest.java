package com.zara.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.prices.config.DataInitializer;
import com.zara.prices.controller.PriceController;
import com.zara.prices.mapper.PriceResponseMapper;
import com.zara.prices.mock.PriceMock;
import com.zara.prices.mock.PriceResponseMock;
import com.zara.prices.model.Price;
import com.zara.prices.repository.PriceRepository;
import com.zara.prices.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    private PriceResponseMapper priceResponseMapper;

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    void testAt10AMOnJune14() throws Exception {
        // Mocking the service response
        when(priceService.getPrice(1L,35455L, LocalDateTime.parse("2020-06-14T10:00:00",DateTimeFormatter.ISO_DATE_TIME)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(35.5));

        when(priceResponseMapper.toResponse(any(Price.class),any(LocalDateTime.class)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(35.5));
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(1L,
                35455L,
                LocalDateTime.parse("2020-06-14T10:00:00",DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-06-14T10:00:00",DateTimeFormatter.ISO_DATE_TIME))).thenReturn(PriceMock.anPriceList());


        // Performing the request and asserting the response
        mockMvc.perform(get("/price")
                        .param("applicationDate", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    void testAt4PMOnJune14() throws Exception {
        // Mocking the service response
        when(priceService.getPrice(1L,35455L, LocalDateTime.parse("2020-06-14T16:00:00",DateTimeFormatter.ISO_DATE_TIME)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(25.45));

        when(priceResponseMapper.toResponse(any(Price.class),any(LocalDateTime.class)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(25.45));
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(1L,
                35455L,
                LocalDateTime.parse("2020-06-14T16:00:00",DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-06-14T16:00:00",DateTimeFormatter.ISO_DATE_TIME))).thenReturn(PriceMock.anPriceList());

        // Performing the request and asserting the response
        mockMvc.perform(get("/price")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void testAt9PMOnJune14() throws Exception {
        // Mocking the service response
        when(priceService.getPrice(1L,35455L, LocalDateTime.parse("2020-06-14T21:00:00",DateTimeFormatter.ISO_DATE_TIME)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(35.5));

        when(priceResponseMapper.toResponse(any(Price.class),any(LocalDateTime.class)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(35.5));
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(1L,
                35455L,
                LocalDateTime.parse("2020-06-14T21:00:00",DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-06-14T21:00:00",DateTimeFormatter.ISO_DATE_TIME))).thenReturn(PriceMock.anPriceList());

        // Performing the request and asserting the response
        mockMvc.perform(get("/price")
                        .param("applicationDate", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.5));
    }

    @Test
    void testAt10AMOnJune15() throws Exception {
        // Mocking the service response
        when(priceService.getPrice(1L,35455L, LocalDateTime.parse("2020-06-15T10:00:00",DateTimeFormatter.ISO_DATE_TIME)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(30.5));

        when(priceResponseMapper.toResponse(any(Price.class),any(LocalDateTime.class)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(30.5));
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(1L,
                35455L,
                LocalDateTime.parse("2020-06-15T10:00:00",DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-06-15T10:00:00",DateTimeFormatter.ISO_DATE_TIME))).thenReturn(PriceMock.anPriceList());

        // Performing the request and asserting the response
        mockMvc.perform(get("/price")
                        .param("applicationDate", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.5));
    }

    @Test
    void testAt9PMOnJune16() throws Exception {
        // Mocking the service response
        when(priceService.getPrice(1L,35455L, LocalDateTime.parse("2020-06-16T21:00:00",DateTimeFormatter.ISO_DATE_TIME)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(38.95));

        when(priceResponseMapper.toResponse(any(Price.class),any(LocalDateTime.class)))
                .thenReturn(PriceResponseMock.caseOnePriceResponse(38.95));
        when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(1L,
                35455L,
                LocalDateTime.parse("2020-06-16T21:00:00",DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse("2020-06-16T21:00:00",DateTimeFormatter.ISO_DATE_TIME))).thenReturn(PriceMock.anPriceList());

        // Performing the request and asserting the response
        mockMvc.perform(get("/price")
                        .param("applicationDate", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
