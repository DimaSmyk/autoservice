package car_service.service;

import car_service.model.Master;

import java.math.BigDecimal;

public interface MasterService {
    Master save(Master master);

    Master findById(Long id);

    BigDecimal calculateSalary(Long id);
}
