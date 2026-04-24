package com.skillslab.jspring.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * CAPSTONE INTEGRATION TEST — expand this with @SpringBootTest +
 * @Testcontainers (Postgres) + MockMvc. You MUST cover at least:
 *
 *   (a) Happy path: POST /orders with a valid body + Idempotency-Key
 *       returns 201 + the OrderResponse.
 *   (b) Validation failure: missing customerId returns 400.
 *   (c) Idempotency replay: same Idempotency-Key returns 200 + the SAME body.
 *   (d) Idempotency conflict: if the first request is still processing,
 *       the second returns 409.
 *
 * The GHA workflow (lab-grade.yml) runs `./mvnw clean verify` and expects
 * this class to ship ≥ 4 assertions passing. Right now it fails on purpose.
 */
class OrdersControllerIntegrationTest {

    @Test
    void capstone_isUnfinished() {
        fail("CAPSTONE TODO — implement the 4 integration-test scenarios described in the Javadoc.");
    }
}
