package tech.brunosantos.btg.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import tech.brunosantos.btg.order.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

  Page<Order> findAllByCustomerId(Long customerId, PageRequest pageRequest);

}
