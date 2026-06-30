package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel save(ProductRecordDto dto) {
        ProductModel model = new ProductModel();
        BeanUtils.copyProperties(dto, model);
        return productRepository.save(model);
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    public ProductModel update(ProductModel existedProduct, ProductRecordDto dto) {
        BeanUtils.copyProperties(dto, existedProduct);
        return productRepository.save(existedProduct);
    }

}
