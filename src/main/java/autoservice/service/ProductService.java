package car_service.service;

import car_service.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAllById(List<Long> productsIds);

}
