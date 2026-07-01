package com.example.springboot.dtos;

import com.example.springboot.models.ProductTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
public class ProductResponseDto extends RepresentationModel<ProductResponseDto> {
    private UUID idProduct;
    private String name;
    private BigDecimal value;
    private LocalDateTime manufacturingDate;
    private LocalDateTime expirationDate;
    private ProductTypeEnum productType;
    private LocalDateTime timeStamp;
}
