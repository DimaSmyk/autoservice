package car_service.service.impl;

import car_service.model.Car;
import car_service.repository.CarRepository;
import car_service.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id: " + id));
    }

    @Override
    public List<Car> findAllById(List<Long> carIds) {
        return carRepository.findAllById(carIds);
    }
}
