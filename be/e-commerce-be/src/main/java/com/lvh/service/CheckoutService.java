package com.lvh.service;

import com.lvh.dto.Purchase;
import com.lvh.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
