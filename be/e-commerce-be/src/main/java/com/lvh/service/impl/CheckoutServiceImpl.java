package com.lvh.service.impl;

import com.lvh.dto.Purchase;
import com.lvh.dto.PurchaseResponse;
import com.lvh.entity.Order;
import com.lvh.entity.OrderItem;
import com.lvh.entity.User;
import com.lvh.repository.UserRepository;
import com.lvh.service.CheckoutService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(orderItem -> order.add(orderItem));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        User user = purchase.getUser();

        String theEmail = user.getEmail();

        User userFromDB = userRepository.findByEmail(theEmail)
                .orElseThrow(() -> new NoSuchElementException("Can not found user email: " + theEmail));

        if(userFromDB != null){
            user = userFromDB;
        }

        user.add(order);

        userRepository.save(user);

        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
