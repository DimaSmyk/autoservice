package car_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarRequestDto {
    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @NotBlank
    private Integer year;
    @NotBlank
    private String licencePlate;
    @NotNull
    private Long carOwnerId;

}
