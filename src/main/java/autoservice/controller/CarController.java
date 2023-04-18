package car_service.controller;

import car_service.dto.request.CarRequestDto;
import car_service.dto.response.CarResponseDto;
import car_service.mapper.impl.CarMapper;
import car_service.model.Car;
import car_service.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping
    @ApiOperation(value = "Create a new car")
    public CarResponseDto create(@RequestBody @Valid @ApiParam(value = "Car parameters")
                                 CarRequestDto requestDto) {
        return carMapper.mapToDto(carService.save(carMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update car")
    public CarResponseDto update(@PathVariable @ApiParam(value = "Car id")
                                 Long id,
                                 @RequestBody @Valid @ApiParam(value = "Car parameters")
                                 CarRequestDto requestDto) {
        Car car = carMapper.mapToModel(requestDto);
        car.setId(id);
        return carMapper.mapToDto(carService.save(car));
    }
}
