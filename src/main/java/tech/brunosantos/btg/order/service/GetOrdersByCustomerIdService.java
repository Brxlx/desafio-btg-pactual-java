package tech.brunosantos.btg.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tech.brunosantos.btg.order.controller.DTO.GetOrdersByCustomerIdResponse;
import tech.brunosantos.btg.order.repository.OrderRepository;

@Service
public class GetOrdersByCustomerIdService {
  private final OrderRepository orderRepository;

  public GetOrdersByCustomerIdService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Page<GetOrdersByCustomerIdResponse> findOrdersByCustomerId(Long customerId, PageRequest pageRequest) {
    var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

    return orders.map(GetOrdersByCustomerIdResponse::fromEntity);
  }
}
