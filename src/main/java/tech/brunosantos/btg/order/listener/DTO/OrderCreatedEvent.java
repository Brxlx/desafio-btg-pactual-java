package tech.brunosantos.btg.order.listener.DTO;

import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(Long orderId, Long customerId, List<OrderItemEvent> items, Instant createdAt,
    Instant updatedAt) {

}
