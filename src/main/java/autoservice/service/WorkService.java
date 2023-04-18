package car_service.service;

import car_service.model.Work;

import java.util.List;

public interface WorkService {
    Work save(Work work);

    Work findById(Long id);

    List<Work> findAllById(List<Long> ids);

    List<Work> findAllByMasterId(Long id);

    Work updatePaidStatus(Long id, Work.Status paidStatus);
}
