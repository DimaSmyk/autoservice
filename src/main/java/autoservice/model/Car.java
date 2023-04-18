package car_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String model;
    private Integer year;
    private String licensePlate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_owner_id")
    private CarOwner carOwner;
}
