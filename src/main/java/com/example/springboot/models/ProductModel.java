package com.example.springboot.models;

import com.example.springboot.repositories.ProductRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductModel implements Serializable   {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private BigDecimal value;
    private LocalDateTime manufacturingDate;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productType;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timeStamp;
}
