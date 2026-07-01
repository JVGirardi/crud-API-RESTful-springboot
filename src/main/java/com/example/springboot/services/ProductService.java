package com.example.springboot.services;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.exceptions.BusinessRuleException;
import com.example.springboot.exceptions.ProductNotFoundException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductModel save(ProductRecordDto dto) {
        validateBusinessRules(dto);

        if (productRepository.existsByNameAndProductType(dto.name(), dto.productType())) {
            throw new BusinessRuleException("Já existe um produto com este nome e categoria cadastrado.");
        }

        ProductModel model = new ProductModel();
        BeanUtils.copyProperties(dto, model);
        return productRepository.save(model);
    }

    public ProductModel findById(UUID id) {

        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isEmpty()) {
            throw new ProductNotFoundException("Produto de ID " + id + " não encontrado.");
        }

        return productModel.get();
    }

    public Page<ProductModel> findAll(Pageable pageable) {

        Page<ProductModel> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    @Transactional
    public void delete(UUID id) {
        ProductModel product = findById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void deleteByProductType(ProductRecordDto dto) {

        productRepository.deleteByProductType(dto.productType());
    }

    @Transactional
    public ProductModel update(UUID existedProductUUID, ProductRecordDto dto) {
        validateBusinessRules(dto);

        ProductModel productModelExisted = findById(existedProductUUID);

        BeanUtils.copyProperties(dto, productModelExisted);
        return productRepository.save(productModelExisted);
    }

    private void validateBusinessRules(ProductRecordDto dto) {
        if (dto.expirationDate().isBefore(dto.manufacturingDate())) {
            throw new BusinessRuleException("A data de validade não pode ser anterior à data de fabricação.");
        }

        if (dto.value().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("O valor do produto deve ser maior que zero.");
        }
    }

}
