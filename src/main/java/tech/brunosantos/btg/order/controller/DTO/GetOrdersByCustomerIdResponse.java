package tech.brunosantos.btg.order.controller.DTO;

import java.math.BigDecimal;

import tech.brunosantos.btg.order.entity.Order;

public record GetOrdersByCustomerIdResponse(Long orderId, Long customerId, BigDecimal total) {
  public static GetOrdersByCustomerIdResponse fromEntity(Order entity) {
    return new GetOrdersByCustomerIdResponse(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
  }
}
