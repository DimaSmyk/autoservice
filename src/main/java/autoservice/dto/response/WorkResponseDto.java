package car_service.dto.response;

import car_service.model.Work;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class WorkResponseDto {
    @NotNull
    private Long id;
    private String name;
    private Long masterId;
    private Long orderId;
    private BigDecimal price;
    private Work.Status status;

}
