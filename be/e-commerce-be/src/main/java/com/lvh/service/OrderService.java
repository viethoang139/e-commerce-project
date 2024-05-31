package com.lvh.service;

import com.lvh.dto.OrderPageResponse;

public interface OrderService {

        OrderPageResponse getOrderByCustomerEmail(String email, int pageNum, int pageSize);



}
