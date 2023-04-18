package car_service.service.impl;

import car_service.model.Work;
import car_service.repository.MasterRepository;
import car_service.model.Master;
import car_service.service.MasterService;
import car_service.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MasterServiceImpl implements MasterService {
    private static final BigDecimal SALARY_PERCENT = new BigDecimal("0.4");
    private final MasterRepository masterRepository;
    private final WorkService serviceService;
    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find master by id: " + id));
    }

    @Override
    public BigDecimal calculateSalary(Long id) {
        Master master = findById(id);
        List<Work> workList = serviceService.findAllByMasterId(id)
                .stream()
                .filter(service -> service.getStatus().equals(Work.Status.UNPAID))
                .collect(Collectors.toList());
        BigDecimal salary = workList.stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        master.setCompletedWork(workList.stream()
                .peek(work -> work.setStatus(Work.Status.PAID))
                .collect(Collectors.toList()));
        save(master);
        return salary.multiply(SALARY_PERCENT);
    }
}
