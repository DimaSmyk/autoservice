package car_service.mapper.impl;

import car_service.dto.request.CarRequestDto;
import car_service.dto.response.CarResponseDto;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import car_service.model.Car;
import car_service.service.CarOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final CarOwnerService carOwnerService;

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setManufacturer(dto.getManufacturer());
        car.setLicensePlate(dto.getLicencePlate());
        car.setCarOwner(carOwnerService.findById(dto.getCarOwnerId()));
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setManufacturer(car.getManufacturer());
        responseDto.setYear(car.getYear());
        responseDto.setModel(car.getModel());
        responseDto.setLicencePlate(car.getLicensePlate());
        return responseDto;
    }
}
