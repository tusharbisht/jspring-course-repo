package com.skillslab.jspring.order;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderSummary(Long id, String customerName, BigDecimal total, Instant createdAt) {}
