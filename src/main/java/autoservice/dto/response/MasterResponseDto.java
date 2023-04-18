package car_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class MasterResponseDto {
    @NotNull
    private Long id;
    private String name;
    private List<Long> completeOrdersIds;
}
