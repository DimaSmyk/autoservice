package car_service.dto.response;

import car_service.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate fromDate;
    private LocalDate endDate;
    private BigDecimal price;
    private Order.Status status;
    private List<Long> worksIds;
    private List<Long> productsIds;

}
