package car_service.controller;

import car_service.dto.request.CarOwnerRequestDto;
import car_service.dto.response.CarOwnerResponseDto;
import car_service.dto.response.OrderResponseDto;
import car_service.mapper.impl.CarOwnerMapper;
import car_service.mapper.impl.OrderMapper;
import car_service.model.CarOwner;
import car_service.service.CarOwnerService;
import car_service.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-owners")
@AllArgsConstructor
public class CarOwnerController {
    private final OrderService orderService;
    private final CarOwnerService carOwnerService;
    private final OrderMapper orderMapper;
    private final CarOwnerMapper carOwnerMapper;

    @PostMapping
    @ApiOperation(value = "Create car owner")
    public CarOwnerResponseDto create(@RequestBody @Valid @ApiParam(value = "Car owner parameters")
                                      CarOwnerRequestDto requestDto) {
        return carOwnerMapper.mapToDto(carOwnerService.save(carOwnerMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update car owner")
    public CarOwnerResponseDto update(@PathVariable @ApiParam(value = "Car owner id")
                                          Long id,
                                      @RequestBody @Valid @ApiParam(value = "Car owner parameters")
                                      CarOwnerRequestDto requestDto) {
        CarOwner carOwner = carOwnerMapper.mapToModel(requestDto);
        carOwner.setId(id);
        return carOwnerMapper.mapToDto(carOwnerService.save(carOwner));
    }

    @GetMapping("/{id}/orders")
    @ApiOperation(value = "Get orders from client by id")
    public List<OrderResponseDto> getOrdersByOwnerId(@PathVariable @ApiParam(value = "Owner id")
                                                     Long id) {
        CarOwner carOwner = carOwnerService.findById(id);
        return carOwner.getOrders()
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
