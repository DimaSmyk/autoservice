package car_service.dto.response;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CarOwnerResponseDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private List<Long> carsIds;
    private List<Long> ordersIds;
}
