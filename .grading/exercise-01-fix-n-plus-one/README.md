# Exercise 01 — Fix the N+1 in `OrderService.getRecentOrders()`

**Module 1 · Hands-on · ~25 min · 🐌 Performance**

---

## The bug

`OrderService.getRecentOrders()` fetches the 20 most recent orders, then —
for each one — issues a separate `SELECT` to load that order's customer
record. **21 queries for 20 orders.** Classic N+1.

You can see it in `src/main/java/com/skillslab/jspring/order/OrderService.java`:

```java
@Transactional(readOnly = true)
public List<OrderSummary> getRecentOrders() {
    List<Order> orders = orderRepository.findTop20ByOrderByCreatedAtDesc();
    return orders.stream()
        .map(o -> new OrderSummary(
            o.getId(),
            o.getCustomer().getName(),  // ← lazy-loads customer ON EACH order
            o.getTotal(),
            o.getCreatedAt()))
        .collect(Collectors.toList());
}
```

`Order.customer` is `@ManyToOne(fetch = FetchType.LAZY)`, so calling
`getCustomer().getName()` inside the stream triggers a fresh query per order.

## What you'll do

Use Claude Code to fix the N+1. Two canonical fixes for Spring Data JPA:

**Option A — `@EntityGraph` on the repository finder.** Lets Spring decide
whether to use a JOIN or a subquery, but guarantees the customer is
eager-loaded:

```java
@EntityGraph(attributePaths = {"customer"})
List<Order> findTop20ByOrderByCreatedAtDesc();
```

**Option B — explicit `JOIN FETCH` JPQL.** More control, slightly more
verbose:

```java
@Query("select o from Order o join fetch o.customer order by o.createdAt desc")
List<Order> findTop20ByOrderByCreatedAtDesc(Pageable pageable);
```

Either fix collapses the 21 queries into 1.

## How to do it

1. **Bootstrap your workspace** (the platform may have done this already):
   ```bash
   git clone https://github.com/tusharbisht/jspring-course-repo
   cd jspring-course-repo
   git checkout module-1-starter
   ```

2. **Open Claude Code in the repo root**:
   ```bash
   claude
   ```
   Once authenticated, ask Claude:
   > Fix the N+1 in `OrderService.getRecentOrders()` using `@EntityGraph`.
   > The bug is that `o.getCustomer().getName()` lazy-loads per order.

   Read the diff Claude proposes — does it touch the right file? Does it
   pick a canonical Spring fix (not a manual prefetch loop)?

3. **Run the grader to verify**:
   ```bash
   bash .grading/run-grading.sh exercise-01-fix-n-plus-one
   ```

   This stages a hidden test that uses Hibernate's `Statistics` to count
   prepared statements during `getRecentOrders()`. The test asserts ≤ 2
   queries. If your fix worked, the last line of stdout is:
   ```
   RESULT: PASS
   ```

   If the test fails, look at the `FAILED:` line for the specific
   assertion and `grading-result.json` for the full count.

## Pedagogical objective

By the end you can:
- **Name** the N+1 query pattern when you see it (and recognize the
  symptom: `1 + N` queries scaling with collection size).
- **Apply** one of the two canonical Spring fixes (`@EntityGraph` /
  `JOIN FETCH`) without looking it up.
- **Verify** the fix with `Statistics.getPrepareStatementCount()` (or
  the `bullet` gem in Rails / `assertNumQueries` in Django / equivalents
  in your stack).
- **Recognize** when Claude suggests something non-canonical (e.g. a
  manual loop that fetches customers separately) and push back.

## What you'll be graded on

Only one signal: does `bash .grading/run-grading.sh
exercise-01-fix-n-plus-one` exit 0?

The hidden test:
1. Spins up a real Postgres via Testcontainers.
2. Seeds 20 customers, each with one order.
3. Calls `orderService.getRecentOrders()` and checks the result has 20
   orders with correct customer names.
4. Asserts Hibernate issued ≤ 2 prepared statements during step 3.

A correct fix issues exactly 1 statement (single `SELECT` with JOIN).
The starter issues 21. Manual workarounds that issue 2-3 statements
still pass — the gate is set generously.

## Common pitfalls

- **Annotation alone isn't enough.** If you add `@EntityGraph` to the
  repository finder but the query is on a different method, the bug
  persists. The grader counts queries during the actual SUT method
  call.
- **Don't change `Customer.orders`'s fetch type.** Making the
  `@OneToMany` eager creates the OPPOSITE N+1 (every customer load
  fetches all their orders). The right fix is on the finder, not the
  inverse side.
- **Don't disable lazy-loading globally** (`hibernate.enable_lazy_load_no_trans`).
  That makes the N+1 invisible but still slow — the grader counts
  queries regardless.

## When you're stuck

Ask Claude:
> Walk me through what queries Hibernate issues for `getRecentOrders()`
> right now, and what `@EntityGraph(attributePaths = {"customer"})`
> changes about that.

Read the explanation; verify it matches what you see in the SUT.
