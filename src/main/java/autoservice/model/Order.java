package car_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
    private String description;
    private LocalDate fromDate;
    private LocalDate endDate;
    private BigDecimal price;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    @Column(name= "products_in_order")
    private List<Product> productsInOrder;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    @Column(name = "works_in_order")
    private List<Work> worksInOrder;
    private Status status = Status.TAKEN;

    public enum Status {
        TAKEN,
        IN_PROCESS,
        COMPLETE,
        FAILED,
        PAID
    }
}
