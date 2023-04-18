package car_service.service.impl;

import car_service.model.Work;
import car_service.repository.WorkRepository;
import car_service.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    @Override
    public Work save(Work work) {
        return workRepository.save(work);
    }

    @Override
    public Work findById(Long id) {
        return workRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find work by id: " + id));
    }

    @Override
    public List<Work> findAllById(List<Long> ids) {
        return workRepository.findAllById(ids);
    }

    @Override
    public List<Work> findAllByMasterId(Long id) {
        return workRepository.findAllByMasterId(id);
    }

    @Override
    public Work updatePaidStatus(Long id, Work.Status paidStatus) {
        Work work = findById(id);
        work.setStatus(paidStatus);
        return work;
    }
}
