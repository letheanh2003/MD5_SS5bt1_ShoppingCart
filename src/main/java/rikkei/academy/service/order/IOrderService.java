package rikkei.academy.service.order;

import rikkei.academy.model.Order;

import java.util.Optional;

public interface IOrderService {
    Iterable<Order> findAll();
    Optional<Order> findById(Long id);
    void save(Order order);

    void remove(Long id);
}
