package car_service.mapper.impl;

import car_service.model.Order;
import car_service.model.Product;
import car_service.model.Work;
import car_service.dto.request.OrderRequestDto;
import car_service.dto.response.OrderResponseDto;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import car_service.service.CarService;
import car_service.service.ProductService;
import car_service.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final ProductService productService;
    private final WorkService workService;
    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.findById(dto.getCarId()));
        order.setDescription(dto.getDescription());
        order.setProductsInOrder(productService.findAllById(dto.getProducts()));
        order.setWorksInOrder(workService.findAllById(dto.getWorks()));
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setFromDate(order.getFromDate());
        orderResponseDto.setEndDate(order.getEndDate());
        orderResponseDto.setDescription(order.getDescription());
        orderResponseDto.setPrice(order.getPrice());
        orderResponseDto.setStatus(order.getStatus());
        orderResponseDto.setCarId(orderResponseDto.getCarId());
        orderResponseDto.setProductsIds(order.getProductsInOrder()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        orderResponseDto.setWorksIds(order.getWorksInOrder()
                .stream()
                .map(Work::getId)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
