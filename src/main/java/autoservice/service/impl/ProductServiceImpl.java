package car_service.service.impl;

import car_service.model.Product;
import car_service.repository.ProductRepository;
import car_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id: " + id));
    }

    @Override
    public List<Product> findAllById(List<Long> productsIds) {
        return productRepository.findAllById(productsIds);
    }
}
