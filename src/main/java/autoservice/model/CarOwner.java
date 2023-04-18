package car_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "car_owners")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Car> cars;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<Order> orders;
}
