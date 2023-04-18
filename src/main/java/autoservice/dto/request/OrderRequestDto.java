package car_service.dto.request;

import car_service.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull
    private Long carId;
    private String description;
    private LocalDate fromDate;
    private List<Long> works;
    private List<Long> products;
    @NotNull
    private Order.Status status;
    private LocalDate endDate;
}
