package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.CategoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    private String name;

    public CategoryDto(CategoryEntity categoryEntity){
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
    }

}
