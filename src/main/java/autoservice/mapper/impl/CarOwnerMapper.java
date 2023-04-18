package car_service.mapper.impl;

import car_service.model.Order;
import car_service.dto.request.CarOwnerRequestDto;
import car_service.dto.response.CarOwnerResponseDto;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import car_service.model.Car;
import car_service.model.CarOwner;
import car_service.service.CarService;
import car_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CarOwnerMapper implements RequestDtoMapper<CarOwnerRequestDto, CarOwner>,
        ResponseDtoMapper<CarOwnerResponseDto, CarOwner> {
    private final CarService carService;
    private final OrderService orderService;
    @Override
    public CarOwner mapToModel(CarOwnerRequestDto dto) {
        CarOwner carOwner = new CarOwner();
        carOwner.setName(dto.getName());
        carOwner.setCars(carService.findAllById(dto.getCarIds()));
        carOwner.setOrders(orderService.findAllByIds(dto.getCarIds()));
        return carOwner;
    }

    @Override
    public CarOwnerResponseDto mapToDto(CarOwner carOwner) {
        CarOwnerResponseDto carOwnerResponseDto = new CarOwnerResponseDto();
        carOwnerResponseDto.setId(carOwner.getId());
        carOwnerResponseDto.setName(carOwner.getName());
        carOwnerResponseDto.setCarsIds(carOwner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        carOwnerResponseDto.setOrdersIds(carOwner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return carOwnerResponseDto;
    }
}
