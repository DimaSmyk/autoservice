package car_service.service.impl;

import car_service.model.CarOwner;
import car_service.repository.CarOwnerRepository;
import car_service.service.CarOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CarOwnerServiceImpl implements CarOwnerService {
    private final CarOwnerRepository carOwnerRepository;

    @Override
    public CarOwner findById(Long id) {
        return carOwnerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car owner by id: " + id));
    }

    @Override
    public CarOwner save(CarOwner carOwner) {
        return carOwnerRepository.save(carOwner);
    }
}
