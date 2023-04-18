package car_service.controller;

import car_service.dto.request.WorkRequestDto;
import car_service.model.Work;
import car_service.dto.response.WorkResponseDto;
import car_service.mapper.impl.WorkMapper;
import car_service.service.WorkService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/works")
@AllArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final WorkMapper workMapper;

    @PostMapping
    @ApiOperation(value = "Create a new work")
    public WorkResponseDto create(@RequestBody @Valid @ApiParam(value = "Work parameters")
                                  WorkRequestDto requestDto) {
        return workMapper.mapToDto(workService.save(workMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update work")
    public WorkResponseDto update(@PathVariable @ApiParam(value = "Work id") Long id,
                                  @RequestBody @Valid @ApiParam(value = "Work parameters")
                                  WorkRequestDto requestDto) {
        Work work = workMapper.mapToModel(requestDto);
        work.setId(id);
        return workMapper.mapToDto(workService.save(work));
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Update work status")
    public WorkResponseDto updateStatus(@PathVariable @ApiParam(value = "Work id") Long id,
                                        @RequestBody @ApiParam(value = "Work status") Work.Status status) {
        Work work = workService.updatePaidStatus(id, status);
        return workMapper.mapToDto(workService.save(work));
    }

}
