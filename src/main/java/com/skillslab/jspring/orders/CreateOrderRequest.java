package com.skillslab.jspring.orders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(
    @NotNull Long customerId,
    @Size(min = 1, max = 50) List<@NotNull @Positive Long> productIds,
    @NotNull @Positive BigDecimal totalAmount
) {}
