package car_service.mapper.impl;

import car_service.dto.request.WorkRequestDto;
import car_service.model.Work;
import car_service.service.MasterService;
import car_service.dto.response.WorkResponseDto;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import car_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WorkMapper implements RequestDtoMapper<WorkRequestDto, Work>,
        ResponseDtoMapper<WorkResponseDto, Work> {
    private final OrderService orderService;
    private final MasterService masterService;
    @Override
    public Work mapToModel(WorkRequestDto dto) {
        Work work = new Work();
        work.setPrice(dto.getPrice());
        work.setOrder(orderService.findById(dto.getOrderId()));
        work.setMaster(masterService.findById(dto.getMasterId()));
        return work;
    }

    @Override
    public WorkResponseDto mapToDto(Work work) {
        WorkResponseDto workResponseDto = new WorkResponseDto();
        workResponseDto.setId(work.getId());
        workResponseDto.setPrice(work.getPrice());
        workResponseDto.setStatus(work.getStatus());
        workResponseDto.setMasterId(work.getMaster().getId());
        workResponseDto.setOrderId(work.getOrder().getId());
        return workResponseDto;
    }
}
