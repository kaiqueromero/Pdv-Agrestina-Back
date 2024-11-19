package com.agrestina.repository;

import com.agrestina.domain.order.Order;
import com.agrestina.dto.statistics.SalesStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByDate(LocalDate now);


    @Query("""
            SELECT SUM(i.unitPrice * i.quantity)
            FROM Order o
            JOIN o.items i
            WHERE o.date = :date
            """)
   BigDecimal TotalDailyRevenue(LocalDate date);



//    @Query("""
//        SELECT NEW com.agrestina.dto.statistics.SalesStatistics(
//            prod.category,
//            SUM(i.quantity),
//            SUM(i.unitPrice * i.quantity)
//        )
//        FROM Order o
//        JOIN o.items i
//        JOIN i.product prod
//        WHERE o.date = :date
//        GROUP BY prod.category
//        """)
//    List<SalesStatistics> TotalDailyRevenueByCategory(LocalDate date);
}
