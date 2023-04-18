package car_service.service;

import car_service.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    List<Order> findAllByIds(List<Long> orderIds);

    BigDecimal getTotalPrice(Order order);

    Order addProducts(Long id, List<Long> productIds);

    Order addWorks(Long id, List<Long> worksIds);

    Order updateOrderStatus(Long orderId, Order.Status orderStatus);

}
