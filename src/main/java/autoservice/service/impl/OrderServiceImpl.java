package car_service.service.impl;

import car_service.model.Order;
import car_service.model.Product;
import car_service.model.Work;
import car_service.service.WorkService;
import car_service.repository.OrderRepository;
import car_service.service.OrderService;
import car_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final WorkService workService;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
    }

    @Override
    public List<Order> findAllByIds(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }

    @Override
    public Order addProducts(Long id, List<Long> productIds) {
        Order order = findById(id);
        List<Product> products = productService.findAllById(productIds);
        order.getProductsInOrder().addAll(products);
        return order;
    }

    @Override
    public Order addWorks(Long id, List<Long> worksIds) {
        Order order = findById(id);
        List<Work> works = workService.findAllById(worksIds);
        order.getWorksInOrder().addAll(works);
        return order;
    }

    @Override
    public Order updateOrderStatus(Long orderId, Order.Status orderStatus) {
        Order order = findById(orderId);
        if (orderStatus.equals(Order.Status.COMPLETE)
                || orderStatus.equals(Order.Status.FAILED)) {
            order.setEndDate(LocalDate.now());
        }
        order.setStatus(orderStatus);
        return order;
    }

    @Override
    public BigDecimal getTotalPrice(Order order) {
        int countOfOwnerOrders = order.getCar().getCarOwner().getOrders().size();
        BigDecimal worksPrice = order.getWorksInOrder().stream()
                .map(Work::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (order.getWorksInOrder().size() > 1) {
            worksPrice = worksPrice.subtract(new BigDecimal("500"));
        }
        BigDecimal productsPrice = order.getProductsInOrder().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal workPriceAfterDiscount =
                getDiscount(2, countOfOwnerOrders, worksPrice);
        BigDecimal productPriceAfterDiscount =
                getDiscount(1, countOfOwnerOrders, productsPrice);
        return workPriceAfterDiscount.add(productPriceAfterDiscount);
    }

    private BigDecimal getDiscount(int discount, int countOfOwnerOrders,
                                   BigDecimal price) {
        int discountPercentage = (discount * countOfOwnerOrders) / 100;
        return price.subtract(price.multiply(new BigDecimal(discountPercentage)));
    }
}
