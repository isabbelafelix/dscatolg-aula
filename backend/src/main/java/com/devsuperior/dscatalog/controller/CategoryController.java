package com.devsuperior.dscatalog.controller;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.entities.CategoryEntity;
import com.devsuperior.dscatalog.service.CategoryService;
import com.devsuperior.dscatalog.service.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> list = this.service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {

        CategoryDto dto = this.service.findById(id);

        return ResponseEntity.ok().body(dto);
    }

}
