package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    //PREPARAR UM DTO PARA RECEBER MAIS DE UMA CATEGORIA
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imgUrl;

    private Instant date;

    //MEU DTO TAMBÉM VAI ACEITAR UMA LISTA DE CATEGORIA
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }

    //SOBRECARGA
    //MESMO CONSTRUTOR COM ARGUMENTOS DIFERENTES

    //CONSTRUTOR RECEBENDO CATEGORIAS
    //QUANDO CHAMAR ESSE CONSTRUTOR, QUERO INSTANCIAR O DTO COLOCANDO OS ELEMENTOS,
    //NA LISTA DE CATEGORYDTO(33)

    public ProductDto(ProductEntity entity, Set<CategoryEntity> categories) {
        this(entity);

        //PRA CADA CATEGORIA(CAT), VOU NA LISTA DE CATEGORIAS,
        //ADICIONANDO UM NEW CATEGORYDTO PASSANDO COMO ARGUMENTO O CAT

        categories.forEach(cat -> this.categories.add(new CategoryDto(cat)));

    }

}
