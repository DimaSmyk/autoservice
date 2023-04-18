package car_service.mapper.impl;

import car_service.model.Work;
import car_service.dto.request.MasterRequestDto;
import car_service.dto.response.MasterResponseDto;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import car_service.model.Master;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class MasterMapper implements RequestDtoMapper<MasterRequestDto, Master>,
        ResponseDtoMapper<MasterResponseDto, Master> {
    @Override
    public Master mapToModel(MasterRequestDto dto) {
        Master master = new Master();
        master.setName(dto.getName());
        master.setCompletedWork(new ArrayList<>());
        return master;
    }

    @Override
    public MasterResponseDto mapToDto(Master master) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(masterResponseDto.getId());
        masterResponseDto.setName(masterResponseDto.getName());
        masterResponseDto.setCompleteOrdersIds(master.getCompletedWork()
                .stream()
                .map(Work::getId)
                .collect(Collectors.toList()));
        return masterResponseDto;
    }
}
