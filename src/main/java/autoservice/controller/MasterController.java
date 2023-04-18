package car_service.controller;

import car_service.dto.request.MasterRequestDto;
import car_service.dto.response.MasterResponseDto;
import car_service.mapper.impl.MasterMapper;
import car_service.model.Master;
import car_service.service.MasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/masters")
@AllArgsConstructor
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper masterMapper;

    @PostMapping
    @ApiOperation(value = "Create master")
    public MasterResponseDto create(@RequestBody @Valid @ApiParam(value = "Master parameters")
                                    MasterRequestDto requestDto) {
        return masterMapper.mapToDto(masterService.save(masterMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update masters")
    public MasterResponseDto update(@PathVariable @ApiParam(value = "Master id")
                                    Long id,
                                    @RequestBody @Valid @ApiParam(value = "Master parameters")
                                    MasterRequestDto requestDto) {
        Master master = masterMapper.mapToModel(requestDto);
        master.setId(id);
        return masterMapper.mapToDto(masterService.save(master));
    }

    @GetMapping("/{id}/salary")
    @ApiOperation(value = "Calculate master salary")
    public BigDecimal getSalary(@PathVariable @ApiParam(value = "Master id")
                                Long id) {
        return masterService.calculateSalary(id);
    }
}
