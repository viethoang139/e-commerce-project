package com.lvh.controller;

import com.lvh.constraint.AppConstraint;
import com.lvh.dto.OrderPageResponse;
import com.lvh.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customerEmail")
    public ResponseEntity<OrderPageResponse> getOrderByCustomerEmail(@RequestParam String email,
                                                                 @RequestParam(name = "pageNum", required = false, defaultValue = AppConstraint.PAGE_NUM) int pageNum,
                                                                 @RequestParam(name = "pageSize", required = false, defaultValue = AppConstraint.PAGE_SIZE) int pageSize){
        return ResponseEntity.ok(orderService.getOrderByCustomerEmail(email,pageNum,pageSize));
    }
}
