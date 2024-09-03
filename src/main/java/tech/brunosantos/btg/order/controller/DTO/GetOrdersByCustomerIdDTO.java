package tech.brunosantos.btg.order.controller.DTO;

import java.util.List;
import java.util.Map;

public record GetOrdersByCustomerIdDTO<T>(Map<String, Object> summary, List<T> data, PaginationResponse pagination) {

}
