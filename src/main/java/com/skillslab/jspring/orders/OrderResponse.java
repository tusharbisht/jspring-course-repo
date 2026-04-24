package com.skillslab.jspring.orders;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(Long id, Long customerId, BigDecimal totalAmount, Instant createdAt) {}
