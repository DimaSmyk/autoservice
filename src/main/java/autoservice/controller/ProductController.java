package car_service.controller;

import car_service.model.Product;
import car_service.dto.request.ProductRequestDto;
import car_service.dto.response.ProductResponseDto;
import car_service.mapper.impl.ProductMapper;
import car_service.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;;

    @PostMapping
    @ApiOperation(value = "Create product")
    private ProductResponseDto create(@RequestBody @Valid @ApiParam(value = "Product parameters")
                                      ProductRequestDto requestDto) {
        return productMapper.mapToDto(productService.save(productMapper.mapToModel(requestDto)));
    }

    @PutMapping
    @ApiOperation(value = "Update product")
    public ProductResponseDto update(@PathVariable @ApiParam(value = "Product id")
                                         Long id,
                                     @RequestBody @Valid @ApiParam(value = "Product parameters")
                                     ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        product.setId(id);
        return productMapper.mapToDto(productService.save(product));
    }

}
