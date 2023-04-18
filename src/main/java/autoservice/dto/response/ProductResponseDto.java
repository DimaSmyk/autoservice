package car_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    @NotNull
    private Long id;
    private String productName;
    private BigDecimal price;
}
