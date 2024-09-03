package tech.brunosantos.btg.order.controller;

import org.springframework.web.bind.annotation.RestController;

import tech.brunosantos.btg.order.controller.DTO.GetOrdersByCustomerIdDTO;
import tech.brunosantos.btg.order.controller.DTO.GetOrdersByCustomerIdResponse;
import tech.brunosantos.btg.order.controller.DTO.PaginationResponse;
import tech.brunosantos.btg.order.service.GetOrdersByCustomerIdService;
import tech.brunosantos.btg.order.service.GetTotalOnOrdersByCustomerIdService;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GetOrdersByCustomerIdController {

  private final GetOrdersByCustomerIdService getOrdersByCustomerIdService;
  private final GetTotalOnOrdersByCustomerIdService getTotalOnOrdersByCustomerIdService;

  public GetOrdersByCustomerIdController(GetOrdersByCustomerIdService getOrdersByCustomerIdService,
      GetTotalOnOrdersByCustomerIdService getTotalOnOrdersByCustomerIdService) {
    this.getOrdersByCustomerIdService = getOrdersByCustomerIdService;
    this.getTotalOnOrdersByCustomerIdService = getTotalOnOrdersByCustomerIdService;
  }

  @GetMapping("customers/{customerId}/orders")
  public ResponseEntity<GetOrdersByCustomerIdDTO<GetOrdersByCustomerIdResponse>> listOrders(
      @PathVariable("customerId") Long customerId, @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

    var pageResponse = getOrdersByCustomerIdService.findOrdersByCustomerId(customerId, PageRequest.of(page, pageSize));
    var totalOnOrders = getTotalOnOrdersByCustomerIdService.execute(customerId);
    return ResponseEntity.ok(new GetOrdersByCustomerIdDTO<>(Map.of("totalOnOrders", totalOnOrders),
        pageResponse.getContent(), PaginationResponse.fromPage(pageResponse)));
  }

}
