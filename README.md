# Module 6 — Capstone: Ship `POST /orders` via GitHub Actions

**Goal:** ship a production-grade `POST /orders` endpoint that passes:
- Bean Validation (`@Valid` on CreateOrderRequest)
- Idempotency-Key handling (201 / 200 / 409)
- Testcontainers-backed integration tests (NOT H2)
- `./mvnw clean verify` locally + `lab-grade.yml` on GitHub Actions

## Flow
1. Fork this repo on your own GitHub.
2. `git checkout module-6-capstone && ./mvnw clean verify` — starter tests fail on purpose.
3. Implement `OrdersController.createOrder` + supporting `IdempotencyStore` + migration SQL for `idempotency_keys`.
4. Finish `OrdersControllerIntegrationTest` with the 4 scenarios in its Javadoc.
5. `git commit -am "capstone: POST /orders with idempotency"` and `git push origin module-6-capstone` (or any branch).
6. GHA runs `lab-grade.yml` — watch it in the Actions tab.
7. Paste the RUN URL (`https://github.com/<you>/<fork>/actions/runs/<id>`) into the course's capstone submit box.

## Idempotency contract
- Client sends `Idempotency-Key: <uuid>` header on every request.
- Same key + same body (within 24h) → return original 201 response body, code 200.
- Same key + different body → return 409 Conflict.
- Key not seen before → persist order + key atomically, return 201.

Store in Postgres: `idempotency_keys(key TEXT PRIMARY KEY, request_hash TEXT, response_body JSONB, status_code INT, created_at TIMESTAMPTZ)`.

## Pass = GHA conclusion `success`
The grader polls the run URL you paste; the `grade` job must complete successfully.
