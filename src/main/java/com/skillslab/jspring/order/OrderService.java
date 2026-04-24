package com.skillslab.jspring.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Returns a summary of the 20 most recent orders, each with customer name.
     *
     * PLANTED BUG (intentional — module 1): this method has an N+1 query problem.
     * For every order returned, Hibernate issues an extra SELECT to fetch the
     * customer because Order.customer is FetchType.LAZY and we call
     * getCustomer().getName() inside the map. 21 queries for 20 orders.
     *
     * The right fix (after M2's CLAUDE.md teaches team conventions): use
     * @EntityGraph on the repository method, OR rewrite the query with
     * JOIN FETCH, so the single query loads all customers eagerly.
     */
    @Transactional(readOnly = true)
    public List<OrderSummary> getRecentOrders() {
        List<Order> orders = orderRepository.findTop20ByOrderByCreatedAtDesc();
        return orders.stream()
            .map(o -> new OrderSummary(
                o.getId(),
                o.getCustomer().getName(), // ← triggers lazy-load PER order (N+1)
                o.getTotal(),
                o.getCreatedAt()))
            .collect(Collectors.toList());
    }
}
