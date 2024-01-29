package com.zara.prices.repository;

import com.zara.prices.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.startDate <= :date AND :date <= p.endDate AND p.brandId = :brandId AND p.productId = :productId  ORDER BY p.priority DESC")
    List<Price> findByDateAndHighestPriority(@Param("date") LocalDateTime date, @Param("brandId") Long brandId, @Param("productId") Long productId);

    List<Price> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);


}