package tech.brunosantos.btg.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.brunosantos.btg.order.entity.Order;
import tech.brunosantos.btg.order.entity.OrderItem;
import tech.brunosantos.btg.order.listener.DTO.OrderCreatedEvent;
import tech.brunosantos.btg.order.repository.OrderRepository;
import java.math.BigDecimal;

@Service
public class CreateOrderService {
  private final OrderRepository orderRepository;

  public CreateOrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void create(OrderCreatedEvent event) {
    var entity = new Order();

    entity.setOrderId(event.orderId());
    entity.setCustomerId(event.customerId());
    entity.setItems(getOrderItems(event));
    entity.setTotal(getTotal(event));
    entity.setCreatedAt(event.createdAt());
    entity.setUpdatedAt(event.updatedAt());

    orderRepository.save(entity);
  }

  private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
    return event.items().stream().map(item -> new OrderItem(item.product(), item.quantity(), item.price())).toList();
  }

  private BigDecimal getTotal(OrderCreatedEvent event) {
    return event.items().stream().map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }

}
