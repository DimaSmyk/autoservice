package car_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CarOwnerRequestDto {
    @NotBlank
    private String name;
    private List<Long> carIds;
    private List<Long> ordersIds;

}
