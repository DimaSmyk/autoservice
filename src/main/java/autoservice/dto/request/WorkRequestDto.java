package car_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class WorkRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private Long masterId;
    @NotNull
    private Long orderId;
    private BigDecimal price;
}
