package tech.brunosantos.btg.order.listener;

import org.springframework.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import tech.brunosantos.btg.order.listener.DTO.OrderCreatedEvent;
import tech.brunosantos.btg.order.service.CreateOrderService;

import static tech.brunosantos.btg.order.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderListener {

  private final Logger logger = LoggerFactory.getLogger(OrderListener.class);
  private final CreateOrderService createOrderService;

  public OrderListener(CreateOrderService createOrderService) {
    this.createOrderService = createOrderService;
  }

  @RabbitListener(queues = ORDER_CREATED_QUEUE)
  public void listen(Message<OrderCreatedEvent> message) {
    logger.info("Message consumed: {}", message);
    // message.getPayload();
    createOrderService.create(message.getPayload());
  }
}
