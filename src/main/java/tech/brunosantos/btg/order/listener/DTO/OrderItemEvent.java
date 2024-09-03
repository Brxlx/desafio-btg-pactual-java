package tech.brunosantos.btg.order.listener.DTO;

import java.math.BigDecimal;

public record OrderItemEvent(String product, Integer quantity, BigDecimal price) {

}
