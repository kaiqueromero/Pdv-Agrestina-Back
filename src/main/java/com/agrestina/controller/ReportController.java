package com.agrestina.controller;
import com.agrestina.dto.statistics.BillingReport;
import com.agrestina.dto.statistics.InventoryReport;
import com.agrestina.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
//    @Autowired
//    private ReportService service;
//
//    @GetMapping("/inventory")
//    public ResponseEntity<InventoryReport> getInfoInventory(){
//        var report = service.infoInventory();
//        return ResponseEntity.ok(report);
//    }
//
//    @GetMapping("/billing")
//    public ResponseEntity<BillingReport> getInfoBilling(){
//        var report = service.revenueObtained();
//        return ResponseEntity.ok(report);
//    }
}
