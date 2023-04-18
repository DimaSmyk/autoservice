package car_service.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank
    private String productName;
    private BigDecimal price;
}
