package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.dtos.ProductResponseDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// STATELESS -> (token) a cada nova requisicao eu recebo todas as informacoes necessarias para fazer aquela funcionalidade que o client esta pedindo
// STATEFULL -> o estado de cada client eh mantido no servidor
//anotacao @restcontroller eh a juncao dos decorators @controller e @responseBody
//a anotacao @requestBody do sb faz a descerializacao para o DTO.
@RestController
@RequestMapping("/products") //--> defino este endpoint global para td classe (n precisaria colocar /products nos metodos no mapping)
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProductController {

    @Autowired //indica para o sb a injecao de ProductRepository
    private ProductService productService;

    @Operation(summary = "Salva um novo produto", description = "Cria um novo produto na base de dados com as validações de regras de negócio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação ou regra de negócio")
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel savedProduct = productService.save(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto(savedProduct));
    }

    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista paginada de todos os produtos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable) {

        Page<ProductModel> productPage = productService.findAll(pageable);

        Page<ProductResponseDto> responsePage = productPage.map(this::toResponseDto);


        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @Operation(summary = "Busca um produto por ID", description = "Retorna os detalhes de um produto específico através do seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado na base de dados") // Padrão obrigatório!
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getOneProduct(@PathVariable("id") UUID id) {
        ProductModel productModel = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(toResponseDto(productModel));
    }

    @Operation(summary = "Atualiza um produto", description = "Atualiza os dados de um produto existente baseado no seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {

        ProductModel updatedProduct = productService.update(id, productRecordDto);
        return ResponseEntity.status(HttpStatus.OK).body(toResponseDto(updatedProduct));
    }

    @Operation(summary = "Deleta um produto", description = "Remove um produto da base de dados através do seu UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso (Sem conteúdo de retorno)"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para deleção")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") UUID id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ProductResponseDto toResponseDto(ProductModel model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        BeanUtils.copyProperties(model, responseDto);

        responseDto.add(linkTo(methodOn(ProductController.class).getOneProduct(responseDto.getIdProduct())).withSelfRel());

        responseDto.add(linkTo(methodOn(ProductController.class).getAllProducts(Pageable.unpaged())).withRel("Products List"));

        return responseDto;
    }
}
