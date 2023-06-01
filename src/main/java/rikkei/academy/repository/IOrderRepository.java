package rikkei.academy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Order;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order, Long> {
}
