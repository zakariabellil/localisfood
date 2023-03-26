package com.LocalisFood.LocalisFood.Service;

import com.LocalisFood.LocalisFood.Exceptions.ProductNotFoundException;
import com.LocalisFood.LocalisFood.Mapper.ProductMapper;
import com.LocalisFood.LocalisFood.Model.Product;
import com.LocalisFood.LocalisFood.Model.User;
import com.LocalisFood.LocalisFood.Repository.ProductRepository;
import com.LocalisFood.LocalisFood.Repository.UserRepository;
import com.LocalisFood.LocalisFood.dto.ProductRequest;
import com.LocalisFood.LocalisFood.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class ProductService {
    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    private final AuthService authService;
    private final ProductMapper productMapper;

    public void save(ProductRequest productRequest) {
       productRepository.save(productMapper.map(productRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public ProductResponse getProducts(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
        return productMapper.mapToDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());
    }


    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return productRepository.findByUser(user)
                .stream()
                .map(productMapper::mapToDto)
                .collect(toList());
    }
}
