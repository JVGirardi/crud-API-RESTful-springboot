package com.example.springboot.dtos;

import com.example.springboot.models.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRecordDto(
        @NotBlank String name,
        @NotNull BigDecimal value,
        @NotNull LocalDateTime manufacturingDate,
        @NotNull LocalDateTime expirationDate,
        @NotNull ProductTypeEnum productType,
        LocalDateTime timeStamp
        ) {
    //Os métodos getters, toString, equals, hashcode, construtor já vem default em records
    //Records são imutaveis, depois de criado não é possível alterar (não há setters)
    //Os atributos são do tipo private e final
}
