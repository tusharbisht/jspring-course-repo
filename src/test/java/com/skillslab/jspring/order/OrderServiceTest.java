package com.skillslab.jspring.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Skeleton test. Module 1: run this, see it pass (or skip).
 * You'll write a proper integration test against Testcontainers in later modules.
 */
class OrderServiceTest {

    @Test
    void smoke_compilesAndRuns() {
        // Minimal check — the real N+1 detection test lives in
        // OrderServiceIntegrationTest which you'll finish in M2/M3.
        assertTrue(true, "smoke test — see OrderService.getRecentOrders for the bug");
    }
}
