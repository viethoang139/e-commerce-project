package com.lvh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data

public class PurchaseResponse {
    @NonNull
    private String orderTrackingNumber;

}