package com.skillslab.jspring.orders;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CAPSTONE SKELETON — finish this endpoint to production grade.
 *
 * Requirements (the lab-grade.yml GHA workflow tests all of these):
 *
 *   1. POST /orders accepts a JSON CreateOrderRequest (see that record).
 *   2. If the body fails Bean Validation, respond 400 with a well-formed
 *      error body (use @ControllerAdvice / ResponseEntityExceptionHandler).
 *   3. Idempotency — the client sends an "Idempotency-Key" header. If a prior
 *      request with the SAME key succeeded, respond 200 with the original
 *      OrderResponse. If a prior request with the SAME key is STILL
 *      PROCESSING, respond 409 Conflict. Otherwise persist and respond 201.
 *      Store idempotency keys in a Postgres table `idempotency_keys` with
 *      (key TEXT PRIMARY KEY, response_body TEXT, status_code INT, created_at TIMESTAMP).
 *   4. Use @Transactional correctly — the persistence + idempotency row must
 *      commit atomically.
 *   5. NO N+1 queries when loading related entities.
 *   6. Integration tests use Testcontainers + Postgres (NOT H2). See
 *      OrdersControllerIntegrationTest for the expected shape.
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    // TODO: inject OrderRepository + IdempotencyStore services here.

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
        @Valid @RequestBody CreateOrderRequest request,
        @RequestHeader("Idempotency-Key") String idempotencyKey
    ) {
        // TODO: implement idempotency check (201 / 200 / 409), validation, persistence.
        throw new UnsupportedOperationException("CAPSTONE TODO — implement this endpoint.");
    }
}
