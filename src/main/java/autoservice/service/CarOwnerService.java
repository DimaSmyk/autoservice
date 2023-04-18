package car_service.service;

import car_service.model.CarOwner;

public interface CarOwnerService {
    CarOwner findById(Long id);
    CarOwner save(CarOwner carOwner);
}
