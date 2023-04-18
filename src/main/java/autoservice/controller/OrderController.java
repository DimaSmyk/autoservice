package car_service.controller;

import car_service.dto.request.OrderRequestDto;
import car_service.dto.response.OrderResponseDto;
import car_service.mapper.impl.OrderMapper;
import car_service.model.Order;
import car_service.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ApiOperation(value = "Create order")
    public OrderResponseDto create(@RequestBody @Validated
                                       @ApiParam(value = "Order parameters")
                                   OrderRequestDto requestDto) {
        return orderMapper.mapToDto(orderService.save(orderMapper.mapToModel(requestDto)));
    }

    @PostMapping("/{id}/products")
    @ApiOperation(value = "Add products to order")
    public OrderResponseDto addProductsToOrder(@PathVariable @ApiParam(value = "Order id")
                                            Long id,
                                        @RequestBody @ApiParam(value = "Products id list")
                                        List<Long> productsIds) {
        return orderMapper.mapToDto(orderService.addProducts(id, productsIds));
    }

    @PostMapping("/{id}/works")
    @ApiOperation(value = "Add works to order")
    public OrderResponseDto addWorksToOrder(@PathVariable @ApiParam(value = "Product id")
                                                Long id,
                                            @RequestBody @ApiParam(value = "Works id list")
                                            List<Long> workIds) {
        return orderMapper.mapToDto(orderService.addWorks(id, workIds));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update order")
    public OrderResponseDto update(@PathVariable @ApiParam(value = "Order id")
                                       Long id,
                                   @RequestBody @Valid @ApiParam(value = "Order parameters")
                                   OrderRequestDto requestDto) {
        Order order = orderMapper.mapToModel(requestDto);
        order.setId(id);
        return orderMapper.mapToDto(orderService.save(order));
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Update order status")
    public OrderResponseDto updateOrderStatus(@PathVariable @ApiParam(value = "Order id")
                                              Long id,
                                              @RequestBody @ApiParam(value = "Order status")
                                              Order.Status status) {
        Order order = orderService.updateOrderStatus(id, status);
        return orderMapper.mapToDto(orderService.save(order));
    }

    @GetMapping("/{id}/cost")
    @ApiOperation(value = "Order cost")
    public BigDecimal getOrderCost(@PathVariable @ApiParam(value = "Order id")
                                   Long id) {
        return orderService.getTotalPrice(orderService.findById(id));
    }

}
