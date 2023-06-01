package rikkei.academy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Product;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
}
