package car_service.mapper.impl;

import car_service.dto.request.ProductRequestDto;
import car_service.dto.response.ProductResponseDto;
import car_service.model.Product;
import car_service.mapper.RequestDtoMapper;
import car_service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    @Override
    public Product mapToModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setPrice(product.getPrice());
        return productResponseDto;
    }
}
