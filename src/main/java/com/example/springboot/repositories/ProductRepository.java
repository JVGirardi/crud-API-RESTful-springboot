package com.example.springboot.repositories;

import com.example.springboot.models.ProductModel;
import com.example.springboot.models.ProductTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    //Custom Queries
    //Utilizando query methods de forma semântica (findBy, existsBy, Before, GreaterThan, And, Or)
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    //SELECT 1 FROM ProductModel p WHERE p.name = :name AND p.productType = :productType
    //@Param("name") String name, @Param("productType") ProductTypeEnum productType
    public boolean existsByNameAndProductType(String name, ProductTypeEnum productType);

    //SELECT p FROM ProductModel p WHERE p.productType = :productType
    //@Param("productType") ProductTypeEnum productType, Pageable pageable
    Page<ProductModel> findByProductType(ProductTypeEnum productType, Pageable pageable);

    @Query("SELECT p FROM ProductModel p WHERE p.value BETWEEN :minPrice AND :maxPrice")
    Page<ProductModel> findProductsInPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

    //na vdd o spring por de baixo dos panos faz um select e depois um delete para cada objeto
    //para fazer a delecao em massa precisaria usar @Modifying
    //DELETE from ProductModel p WHERE p.productType = :productType
    //@Param("productType") ProductTypeEnum productTypeEnum
    public void deleteByProductType(ProductTypeEnum productTypeEnum);

    //SELECT COUNT(p) > 0 FROM ProductModel p WHERE p.productType = :productType LIMIT 1
    //@Param("productType") ProductTypeEnum productTypeEnum
    public boolean existsByProductType(ProductTypeEnum productTypeEnum);
}
