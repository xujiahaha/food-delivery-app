package demo.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    Order findById(String orderId);

    List<Order> findByUserId(Sort sort, String userId);

}
